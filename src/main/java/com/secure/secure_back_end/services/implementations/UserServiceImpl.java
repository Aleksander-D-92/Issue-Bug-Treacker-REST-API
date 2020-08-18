package com.secure.secure_back_end.services.implementations;


import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.configuration.exceptions.UserNotFoundException;
import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.*;
import com.secure.secure_back_end.repositories.AuthorityRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import com.secure.secure_back_end.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private static final String DEFAULT_ROLE = "ROLE_USER";

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
        if (this.userRepository.findByUsername(userRegistrationForm.getUsername()) != null)
        {
            throw new UserAlreadyExistsException("Username already exists in DB");
        }
        String authorityValue = userRegistrationForm.getAuthority();
        Authority authority;
        //back end validation for authority
        if (authorityValue == null || authorityValue.equals("")) //if no  value has been passed assign user with basic role
        {
            authority = this.authorityRepository.findByAuthority(DEFAULT_ROLE);
        } else //else use the the authorityValue
        {
            authority = this.authorityRepository.findByAuthority(authorityValue);
        }
        if (authority == null) // if it's still null because the front end validation has been cracked and invalid value has been passed
        {
            authority = this.authorityRepository.findByAuthority(DEFAULT_ROLE);
        }

        User newUser = this.modelMapper.map(userRegistrationForm, User.class);
        newUser.setRegistrationDate(new Date());
        newUser.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
        newUser.setAccountNonLocked(true);
        newUser.getAuthorities().add(authority);
        this.userRepository.save(newUser);
    }

    @Override
    public void changeUserRole(UserChangeAuthorityForm userChangeAuthorityForm)
    {
        User user = this.userRepository.findById(userChangeAuthorityForm.getUserId()).orElse(null);
        Authority byAuthority = this.authorityRepository.findByAuthority(userChangeAuthorityForm.getAuthority());
        assert user != null;
        user.getAuthorities().clear();
        user.getAuthorities().add(byAuthority);
        this.userRepository.save(user);
    }

    @Override
    public UsersTable getUsersPage(int pageNumber)
    {
        Pageable pageable;
        if (pageNumber < 0) // we return the entire user database
        {
            pageable = PageRequest.of(0, Integer.MAX_VALUE);
        } else // we return that page number, each page is hardcoded to be size 10
        {
            pageable = PageRequest.of(pageNumber, 10);
        }
        Page<User> currentPage = this.userRepository.findAll(pageable);
        int totalPages = currentPage.getTotalPages();
        List<UserAuthorityDetails> userModels = currentPage.getContent().stream().map(user ->
        {
            UserAuthorityDetails mappedUser = this.modelMapper.map(user, UserAuthorityDetails.class);
            Authority highestAuthority = user.getAuthorities().stream().reduce((e1, e2) -> e1.getAuthorityLevel() > e2.getAuthorityLevel() ? e1 : e2).get();
            mappedUser.setAuthority(highestAuthority);
            return mappedUser;
        }).collect(Collectors.toList());

        return new UsersTable(userModels, totalPages);
    }

    @Override
    public UserAuthorityDetails getUserDetailsById(long userId)
    {
        User user = this.userRepository.findById(userId).orElse(null);
        assert user != null;
        return convertUserToUserAuthorityDetails(user);
    }

    @Override
    public UserAuthorityDetails getUserDetailsByUsername(String username)
    {
        User user = this.userRepository.findByUsername(username);
        return convertUserToUserAuthorityDetails(user);
    }

    @Override
    public void deleteByUsername(UserDeleteAccountForm userDeleteAccountForm) throws PasswordMissMatchException
    {
        User user = this.userRepository.findByUsername(userDeleteAccountForm.getUsername());
        if (!this.passwordEncoder.matches(userDeleteAccountForm.getPassword(), user.getPassword()))
        {
            throw new PasswordMissMatchException("Passwords do not match");
        }
        this.userRepository.delete(user);
    }

    @Override
    public void changePasswordUsername(UserChangePasswordForm userChangePasswordForm) throws PasswordMissMatchException
    {
        User user = this.userRepository.findByUsername(userChangePasswordForm.getUsername());
        if (!this.passwordEncoder.matches(userChangePasswordForm.getPassword(), user.getPassword()))
        {
            throw new PasswordMissMatchException("Passwords do not match");
        }
        user.setPassword(this.passwordEncoder.encode(userChangePasswordForm.getNewPassword()));
        this.userRepository.save(user);
    }


    private UserAuthorityDetails convertUserToUserAuthorityDetails(User user)
    {
        UserAuthorityDetails userAuthorityDetails = this.modelMapper.map(user, UserAuthorityDetails.class);
        Authority highestAuthority = user.getAuthorities().stream().reduce((e1, e2) -> e1.getAuthorityLevel() > e2.getAuthorityLevel() ? e1 : e2).get();
        userAuthorityDetails.setAuthority(highestAuthority);
        return userAuthorityDetails;
    }

}
