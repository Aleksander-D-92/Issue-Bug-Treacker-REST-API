package com.secure.secure_back_end.controllers.user;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.user.UserAuthorityDetails;
import com.secure.secure_back_end.dto.user.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.UserDeleteAccountForm;
import com.secure.secure_back_end.dto.user.UserRegistrationForm;
import com.secure.secure_back_end.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController
{
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }


    @PostMapping(value = "/users/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationForm userRegistrationForm)
    {
        this.logger.debug(String.valueOf(userRegistrationForm));
        try
        {
            this.userService.register(userRegistrationForm);
        } catch (UserAlreadyExistsException e)
        {
            this.logger.error("error log");
            return new ResponseEntity<>("User Already exists", HttpStatus.CONFLICT);
        }
        this.logger.debug("debug log");
        return new ResponseEntity<>("registered", HttpStatus.OK);
    }


    @DeleteMapping("/users/delete")
    public ResponseEntity<String> delete(@Valid @RequestBody UserDeleteAccountForm userDeleteAccountForm)
    {
        try
        {
            this.userService.deleteByUsername(userDeleteAccountForm);
        } catch (PasswordMissMatchException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successful delete", HttpStatus.OK);
    }


    @PutMapping("/users/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody UserChangePasswordForm userChangePasswordForm)
    {
        try
        {
            this.userService.changePasswordUsername(userChangePasswordForm);
        } catch (PasswordMissMatchException e)
        {
            return new ResponseEntity<>("New and Old Passwords do not match", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successful delete", HttpStatus.OK);
    }


    @GetMapping("/users/get-user-details-by-username/{username}")
    public ResponseEntity<UserAuthorityDetails> getUserAuthorityDetails(@PathVariable(value = "username") String username)
    {
        UserAuthorityDetails userDetailsByUsername = this.userService.getUserDetailsByUsername(username);
        return new ResponseEntity<>(userDetailsByUsername, HttpStatus.OK);
    }
}
