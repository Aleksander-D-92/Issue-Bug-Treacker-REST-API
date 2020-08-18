package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.dto.authority.UserChangeAuthorityForm;
import com.secure.secure_back_end.dto.user.UserAuthorityDetails;
import com.secure.secure_back_end.dto.user.UsersTable;
import com.secure.secure_back_end.services.implementations.AuthorityServiceImpl;
import com.secure.secure_back_end.services.interfaces.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admins/get-all-users/{pageNumber}")
    @ApiOperation(value = "Get users table", notes = "if you don't want pagination then set {pageNumber} to -1")
    public ResponseEntity<UsersTable> getUsersTable(@PathVariable(value = "pageNumber", required = false) int pageNumber)
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

    @PutMapping("/admins/update-users-authority-by-id/")
    public void getUsersTable(@Valid @RequestBody UserChangeAuthorityForm userChangeAuthorityForm)
    {
        this.userService.changeUserRole(userChangeAuthorityForm);
    }
}
