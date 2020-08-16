package com.secure.secure_back_end.controllers.user;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.dto.authority.SimpleAuthority;
import com.secure.secure_back_end.dto.user.UserAuthorityDetails;
import com.secure.secure_back_end.dto.user.UsersTable;
import com.secure.secure_back_end.services.AuthorityServiceImpl;
import com.secure.secure_back_end.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController
{
    private final UserServiceImpl userService;
    private final AuthorityServiceImpl authorityService;

    @Autowired
    public AdminController(UserServiceImpl userService, AuthorityServiceImpl authorityService)
    {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    //get {pageNumber} to -1 if you want all  users
    @GetMapping("/admins/get-all-users/{pageNumber}")
    public ResponseEntity<UsersTable> manageUserRoles(@PathVariable(value = "pageNumber", required = false) int pageNumber)
    {
        UsersTable usersPage = this.userService.getUsersPage(pageNumber);
        return new ResponseEntity<>(usersPage, HttpStatus.OK);
    }

    @GetMapping("/admins/get-all-authorities")
    public ResponseEntity<List<Authority>> getAllAuthorities()
    {
        return new ResponseEntity<>(this.authorityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/admins/get-user-details-by-id/{userId}")
    public ResponseEntity<UserAuthorityDetails> getUserAuthorityDetails(@PathVariable(value = "userId") long userId)
    {
        UserAuthorityDetails userAuthorityDetails = this.userService.getUserDetailsById(userId);
        return new ResponseEntity<>(userAuthorityDetails, HttpStatus.OK);
    }

    @PutMapping("/admins/update-users-authority-by-id/{userId}")
    public void manageUserRoles(@PathVariable(value = "userId") long userId, @RequestBody SimpleAuthority simpleAuthority)
    {
        this.userService.changeUserRole(userId, simpleAuthority.getAuthority());
    }
}
