package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserDeleteAccountForm;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class UserController
{
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/users/details/{userId}")
    @ApiOperation(value = "returns user details or account information of the current user")
    public ResponseEntity<UserViewModel> getUserAuthorityDetails(@PathVariable(value = "userId") @Min(1) Long id)
    {
        UserViewModel userDetailsByUsername = this.userService.getUserDetailsById(id);
        return new ResponseEntity<>(userDetailsByUsername, HttpStatus.OK);
    }

    @GetMapping("/users/all-developers")
    @ApiOperation(value = "returns all of the developers currently registered")
    public List<UserViewModel> getAllDevelopers()
    {
        return this.userService.getAllDevelopers();
    }

    @PostMapping(value = "/users/register")
    @ApiOperation(value = "creates a new account")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationForm form)
    {
        try
        {
            this.userService.register(form);
        } catch (UserAlreadyExistsException e)
        {
            return new ResponseEntity<>("User Already exists", HttpStatus.CONFLICT);
        }
        this.logger.debug("debug log");
        return new ResponseEntity<>("registered", HttpStatus.OK);
    }

    @DeleteMapping("/users/account/{userId}")
    @ApiOperation(value = "deletes an account if you provided a correct password")
    public ResponseEntity<String> deleteAccount(@Valid @RequestBody UserDeleteAccountForm form,
                                                @PathVariable("userId") @Min(1) Long userId)
    {
        try
        {
            this.userService.deleteAccount(form, userId);
        } catch (PasswordMissMatchException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successful delete", HttpStatus.OK);
    }


    @PutMapping("/users/password/{userId}")
    @ApiOperation(value = "changes the password of the given user")
    public ResponseEntity<String> changePassword(@Valid @RequestBody UserChangePasswordForm form,
                                                 @PathVariable("userId") @Min(1) Long userId)
    {
        try
        {
            this.userService.changePassword(form, userId);
        } catch (PasswordMissMatchException e)
        {
            return new ResponseEntity<>("New and Old Passwords do not match", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successfully changed passwords", HttpStatus.OK);
    }
}
