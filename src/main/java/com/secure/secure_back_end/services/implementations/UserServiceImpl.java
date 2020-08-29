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
    private static final Long ROLE_SUBMITTER = 1L;

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
    public void register(UserRegistrationForm userRegistrationForm) throws UserAlreadyExistsException
    {
        //check if user exist
        if (this.userRepository.findByUsername(userRegistrationForm.getUsername()) != null)
        {
            throw new UserAlreadyExistsException("Username already exists in DB");
        }
        Long authorityId = userRegistrationForm.getAuthorityId();
        Authority authority;
        //if no  value has been passed assign user with basic role
        if (authorityId == null || authorityId >= 5 || authorityId <= 0)
        {
            authority = this.authorityRepository.getOne(ROLE_SUBMITTER);
        } else //else use the the authorityValue
        {
            authority = this.authorityRepository.getOne(authorityId);
        }

        User newUser = this.modelMapper.map(userRegistrationForm, User.class);
        newUser.setRegistrationDate(new Date());
        newUser.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
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
    public List<UserViewModel> getAllUsers()
    {
        return this.userRepository.getAllUsers().stream().map(this::mapToUserViewModel).collect(Collectors.toList());

    }

    @Override
    public UserViewModel getSingleUser(long userId)
    {
        User user = this.userRepository.getSingle(userId);
        return mapToUserViewModel(user);
    }


    @Override
    public List<UserViewModel> getAllByAuthority(Long authorityId)
    {
        return this.userRepository.getUsersByAuthority(authorityId).stream().map(this::mapToUserViewModel).collect(Collectors.toList());
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


    private UserViewModel mapToUserViewModel(User user)
    {
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        Authority highestAuthority = user.getAuthorities().stream().reduce((e1, e2) -> e1.getAuthorityLevel() > e2.getAuthorityLevel() ? e1 : e2).get();
        userViewModel.setAuthority(highestAuthority);
        return userViewModel;
    }

}
