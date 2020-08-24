package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.configuration.exceptions.UserAlreadyExistsException;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserDeleteAccountForm;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.implementations.AuthorityServiceImpl;
import com.secure.secure_back_end.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController
{
    private final UserService userService;
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService, AuthorityServiceImpl authorityService)
    {
        this.userService = userService;
    }


    @PostMapping(value = "/users/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationForm form)
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

    @DeleteMapping("/users/account")
    public ResponseEntity<String> delete(@Valid @RequestBody UserDeleteAccountForm form)
    {
        try
        {
            this.userService.deleteAccount(form);
        } catch (PasswordMissMatchException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successful delete", HttpStatus.OK);
    }


    @PutMapping("/users/password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody UserChangePasswordForm form)
    {
        try
        {
            this.userService.changePassword(form);
        } catch (PasswordMissMatchException e)
        {
            return new ResponseEntity<>("New and Old Passwords do not match", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("Successfully changed passwords", HttpStatus.OK);
    }


    @GetMapping("/users/details/{userId}")
    public ResponseEntity<UserViewModel> getUserAuthorityDetails(@PathVariable(value = "userId") Long id)
    {
        UserViewModel userDetailsByUsername = this.userService.getUserDetailsById(id);
        return new ResponseEntity<>(userDetailsByUsername, HttpStatus.OK);
    }

    @GetMapping("/users/all-developers")
    public List<UserViewModel> getAllDevelopers()
    {
        return this.userService.getAllDevelopers();
    }

}
