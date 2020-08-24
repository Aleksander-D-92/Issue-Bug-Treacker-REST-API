package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.view.UserViewModel;
import com.secure.secure_back_end.services.implementations.AuthorityServiceImpl;
import com.secure.secure_back_end.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController
{
    private final UserService userService;
    private final AuthorityServiceImpl authorityService;

    @Autowired
    public AdminController(UserService userService, AuthorityServiceImpl authorityService)
    {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping("/admins/all-users")
    public List<UserViewModel> getUsersTable()
    {
        return this.userService.getAllUsers();
    }

    @PutMapping("/admins/user-authority")
    public void getUsersTable(@Valid @RequestBody UserChangeAuthorityForm form)
    {
        this.userService.changeUserRole(form);
    }
}
