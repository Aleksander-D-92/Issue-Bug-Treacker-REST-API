package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController
{
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/admins/all-users")
    public List<UserViewModel> getAllUsers()
    {
        return this.userService.getAllUsers();
    }

    @PutMapping("/admins/authority/{userId}")
    public void changeAuthority(@Valid @RequestBody UserChangeAuthorityForm form, @PathVariable("userId") Long userId)
    {
        this.userService.changeUserRole(form, userId);
    }
}
