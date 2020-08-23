package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserDeleteAccountForm;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.dto.user.view.UsersTable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
    void register(UserRegistrationForm userRegistrationForm) throws UserAlreadyExistsException;


    void changeUserRole(UserChangeAuthorityForm userChangeAuthorityForm);


    UsersTable getUsersPage(int pageNumber);


    UserViewModel getUserDetailsById(long userId);


    void deleteByUsername(UserDeleteAccountForm userDeleteAccountForm) throws PasswordMissMatchException;


    void changePasswordUsername(UserChangePasswordForm userChangePasswordForm) throws PasswordMissMatchException;

    List<UserViewModel> getAllDevelopers();
}
