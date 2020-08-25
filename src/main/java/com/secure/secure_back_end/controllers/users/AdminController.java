package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class AdminController
{
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/admins/all-users")
    @ApiOperation(value = "returns all of the users currently registered")
    public List<UserViewModel> getAllUsers()
    {
        return this.userService.getAllUsers();
    }

    @PutMapping("/admins/authority/{userId}")
    @ApiOperation(value = "returns changes a user authority")
    public void changeAuthority(@Valid @RequestBody UserChangeAuthorityForm form,
                                @PathVariable("userId") @Min(1) Long userId)
    {
        this.userService.changeUserRole(form, userId);
    }
}
