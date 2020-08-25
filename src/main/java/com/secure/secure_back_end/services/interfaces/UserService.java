package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserDeleteAccountForm;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
    void register(UserRegistrationForm userRegistrationForm) throws UserAlreadyExistsException;

    void changeUserRole(UserChangeAuthorityForm form, Long userId);

    List<UserViewModel> getAllUsers();

    UserViewModel getUserDetailsById(long userId);

    void deleteAccount(UserDeleteAccountForm userDeleteAccountForm, Long userId) throws PasswordMissMatchException;

    void changePassword(UserChangePasswordForm userChangePasswordForm, Long userId) throws PasswordMissMatchException;

    List<UserViewModel> getAllDevelopers();
}
