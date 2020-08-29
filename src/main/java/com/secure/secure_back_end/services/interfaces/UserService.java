package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserLockAccount;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService
{
    void register(UserRegistrationForm userRegistrationForm) throws UserAlreadyExistsException;

    void changeUserRole(Long authorityId, Long userId);

    List<UserViewModel> getAllUsers();

    UserViewModel getSingleUser(long userId);

    void lockAccount(UserLockAccount userLockAccount, Long userId) throws PasswordMissMatchException;

    void changePassword(UserChangePasswordForm userChangePasswordForm, Long userId) throws PasswordMissMatchException;

    List<UserViewModel> getAllByAuthority(Long authorityId);

    void lockAccountAdmin(String action, Long id);
}
