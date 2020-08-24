package com.secure.secure_back_end.services.implementations;


import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserDeleteAccountForm;
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
    private static final Long ROLE_DEVELOPER = 2L;

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
    public void changeUserRole(UserChangeAuthorityForm form, Long userId)
    {

        this.userRepository.deleteAuthority(userId);
        this.userRepository.insertAuthority(userId, form.getAuthorityId());
    }

    @Override
    public List<UserViewModel> getAllUsers()
    {
        return this.userRepository.getUserDetailsAll().stream().map(this::mapToUserViewModel).collect(Collectors.toList());

    }

    @Override
    public UserViewModel getUserDetailsById(long userId)
    {
        User user = this.userRepository.getUserDetails(userId);
        return mapToUserViewModel(user);
    }


    @Override
    public void deleteAccount(UserDeleteAccountForm form, Long userId) throws PasswordMissMatchException
    {
        User user = this.userRepository.getOne(userId);
        if (!this.passwordEncoder.matches(form.getPassword(), user.getPassword()))
        {
            throw new PasswordMissMatchException("Passwords do not match");
        }
        this.userRepository.delete(user);
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

    @Override
    public List<UserViewModel> getAllDevelopers()
    {
        return this.userRepository.getAllDevelopers(ROLE_DEVELOPER).stream().map(this::mapToUserViewModel).collect(Collectors.toList());
    }


    private UserViewModel mapToUserViewModel(User user)
    {
        UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);
        Authority highestAuthority = user.getAuthorities().stream().reduce((e1, e2) -> e1.getAuthorityLevel() > e2.getAuthorityLevel() ? e1 : e2).get();
        userViewModel.setAuthority(highestAuthority);
        return userViewModel;
    }

}
