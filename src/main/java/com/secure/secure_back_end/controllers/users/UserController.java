package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.dto.rest_success.Message;
import com.secure.secure_back_end.dto.user.binding.UserChangePasswordForm;
import com.secure.secure_back_end.dto.user.binding.UserLockAccount;
import com.secure.secure_back_end.dto.user.binding.UserRegistrationForm;
import com.secure.secure_back_end.dto.user.view.UserDetailsView;
import com.secure.secure_back_end.services.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@Validated
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ApiOperation(value = "action must equal to \"single\" , \"by-authority\" or \"all\" If action equals \"by-authority\" you have to provide authority id. Example GET /users?action=by-authority&id=2")
    public List<UserDetailsView> getUsers(@RequestParam("action") @Pattern(regexp = "^single$|^by-authority$|^all$") String action,
                                          @RequestParam(value = "id", required = false) @Min(1) Long id)
    {
        switch (action)
        {
            case "single":
                return Collections.singletonList(this.userService.findOne(id));
            case "by-authority":
                return this.userService.findAllByAuthority(id);
            case "all":
                return this.userService.findAll();
            default:
                return new ArrayList<>();
        }
    }

    @PostMapping(value = "/users/register")
    @ApiOperation(value = "creates a new account, you can Optionally provide a managerId. Example POST /users/register?managerId=3")
    public ResponseEntity<Message> registerUser(@Valid @RequestBody UserRegistrationForm form,
                                                @RequestParam(value = "managerId", required = false) @Min(1) Long managerId)
    {
        if (managerId != null)
        {
            this.userService.registerStaff(form, managerId);
        } else
        {
            this.userService.register(form);
        }
        return new ResponseEntity<>(new Message("Successfully registered"), HttpStatus.OK);
    }

    @PutMapping("/users/password/{userId}")
    @ApiOperation(value = "changes the password of the given user")
    public ResponseEntity<Message> changePassword(@Valid @RequestBody UserChangePasswordForm form,
                                                  @PathVariable("userId") @Min(1) Long userId) throws PasswordMissMatchException
    {

        this.userService.changePassword(form, userId);
        return new ResponseEntity<>(new Message("Successfully changed password"), HttpStatus.OK);
    }

    @PutMapping("/users/account-lock/{userId}")
    @ApiOperation(value = "User delete account request ends up here. Locks users own account if they provided the correct password.")
    public ResponseEntity<Message> lockAccount(@Valid @RequestBody UserLockAccount form,
                                              @PathVariable("userId") @Min(1) Long userId) throws PasswordMissMatchException
    {
        this.userService.lockAccount(form, userId);
        return new ResponseEntity<>(new Message("You have successfully deleted your account"), HttpStatus.OK);
    }
}
