package com.secure.secure_back_end.services.implementations;


import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserLockAccount;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.repositories.AuthorityRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import com.secure.secure_back_end.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = this.userRepository.findByUsername(username);
        if (user == null)
        {
            throw new UserNotFoundException("User not found");
        }

        return user;
    }

    @Override
    public void register(UserRegistrationForm form) throws UserAlreadyExistsException
    {
        //check if user exist
        if (this.userRepository.findByUsername(form.getUsername()) != null)
        {
            throw new UserAlreadyExistsException("Username with this name is all ready registered");
        }
        Authority authority = this.authorityRepository.getOne(form.getAuthorityId());

        User newUser = this.modelMapper.map(form, User.class);
        newUser.setRegistrationDate(new Date());
        newUser.setPassword(passwordEncoder.encode(form.getPassword()));
        newUser.setAccountNonLocked(true);
        newUser.getAuthorities().add(authority);
        this.userRepository.save(newUser);
    }

    @Override
    public void changeUserRole(Long authorityId, Long userId)
    {
        this.userRepository.deleteAuthority(userId);
        this.userRepository.insertAuthority(userId, authorityId);
    }

    @Override
    public UserViewModel getSingle(long userId)
    {
        //todo test graph
        User user = this.userRepository.findByUserId(userId);
        UserViewModel map = this.modelMapper.map(user, UserViewModel.class);
        map.setAuthority(user.getAuthorities().iterator().next());
        return map;
    }

    @Override
    public List<UserViewModel> getAll()
    {
        return this.userRepository.getAll().stream()
                .map(user ->
                {
                    UserViewModel map = this.modelMapper.map(user, UserViewModel.class);
                    map.setAuthority(user.getAuthorities().iterator().next());
                    return map;
                }).collect(Collectors.toList());

    }

    @Override
    public List<UserViewModel> getAllByAuthority(Long authorityId)
    {
        return this.userRepository.getAllByAuthority(authorityId).stream()
                .map(user ->
                {
                    UserViewModel map = this.modelMapper.map(user, UserViewModel.class);
                    map.setAuthority(user.getAuthorities().iterator().next());
                    return map;
                }).collect(Collectors.toList());
    }

    @Override
    public void lockAccount(UserLockAccount form, Long userId) throws PasswordMissMatchException
    {
        User user = this.userRepository.getOne(userId);
        if (!this.passwordEncoder.matches(form.getPassword(), user.getPassword()))
        {
            throw new PasswordMissMatchException("Passwords do not match");
        }
        this.userRepository.setIsAccountNonLocked(userId, false);
    }

    @Override
    public void lockAccountAdmin(String action, Long id)
    {
        this.userRepository.setIsAccountNonLocked(id, !action.equals("lock"));
    }

    @Override
    public void changePassword(UserChangePasswordForm form, Long userId) throws PasswordMissMatchException
    {
        User user = this.userRepository.findById(userId).orElse(null);
        assert user != null;
        if (!this.passwordEncoder.matches(form.getOldPassword(), user.getPassword()))
        {
            throw new PasswordMissMatchException("Passwords do not match");
        }
        user.setPassword(this.passwordEncoder.encode(form.getNewPassword()));
        this.userRepository.save(user);
    }
}
