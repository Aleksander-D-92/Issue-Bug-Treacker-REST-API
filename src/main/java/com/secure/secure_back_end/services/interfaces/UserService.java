package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.UserAuthorityDetails;
import com.secure.secure_back_end.dto.user.UsersTable;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserDeleteAccountForm;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService
{
    /**
     * @param userRegistrationForm UserRegistrationForm.class
     * @throws UserAlreadyExistsException <ul>
     *                                    <li>inserts a new user in to the database</li>
     *                                    <li>if a user with the same username already exist, you will get <b>UserAlreadyExistsException</b></li>
     *                                    <li>it automatically assigns a creation date</b></li>
     *                                    <li>if role is not provided or is null it will use DEFAULT_ROLE </b></li>
     *                                    </ul>
     */
    void register(UserRegistrationForm userRegistrationForm) throws UserAlreadyExistsException;

    /**
     * @param userChangeAuthorityForm contains userId and authority string <h1>Intended to be admin only method</h1>
     *                                <ul>
     *                                     <li>Fionds user by id</li>
     *                                     <li>Removes his current authority</li>
     *                                     <li>Adds the new authority witch we pass, because we want to keep authorities to just one</li>
     *                                 </ul>
     */
    void changeUserRole(UserChangeAuthorityForm userChangeAuthorityForm);

    /**
     * <h1>Intended to be admin only method</h1>
     *
     * @param pageNumber int witch page we want to get
     * @return UsersTable {@literal <}UserAuthorityDetails>
     * <ul>
     *     <li><b>{@literal <}UsersTable></b> object witch contains a Page(10 users) of users</li>
     *     <li><b>{@literal <}UserAuthorityDetails></b> contains all of the  user account information</li>
     *     <li>if <b>pageNumber</b> is less then <b>0</b> it will return the entire table</li>
     * </ul>
     */
    UsersTable getUsersPage(int pageNumber);

    /**
     * @param userId - long
     * @return UserAuthorityDetails
     * <ul>
     *     <li><b>{@literal <}UserAuthorityDetails></b> contains all of the  user account information</li>
     *     <li>it will call convertUserToUserAuthorityDetails to reduce the authorities to only the highest one</li>
     * </ul>
     */
    UserAuthorityDetails getUserDetailsById(long userId);

    /**
     * @param username - string
     * @return UserAuthorityDetails
     * <ul>
     *     <li><b>{@literal <}UserAuthorityDetails></b> contains all of the  user account information</li>
     *     <li>it will call convertUserToUserAuthorityDetails to reduce the authorities to only the highest one</li>
     * </ul>
     */

    UserAuthorityDetails getUserDetailsByUsername(String username);

    /**
     * @param userDeleteAccountForm contains username and password
     * @throws PasswordMissMatchException <ul>
     *                                    <li>Deletes a user account by username and password information</li>
     *                                    <li>if the provided password and the original one do not match it will throw <b>PasswordMissMatchException</b></li>
     *                                    </ul>
     */
    void deleteByUsername(UserDeleteAccountForm userDeleteAccountForm) throws PasswordMissMatchException;

    /**
     * @param userChangePasswordForm contains username and password
     * @throws PasswordMissMatchException <ul>
     *                                    <li>Changes users password byt username and password</li>
     *                                    <li>if the provided password and the original one do not match it will throw <b>PasswordMissMatchException</b></li>
     *                                    </ul>
     */
    void changePasswordUsername(UserChangePasswordForm userChangePasswordForm) throws PasswordMissMatchException;
}
