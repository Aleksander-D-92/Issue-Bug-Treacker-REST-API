package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.services.interfaces.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorityController
{
    private final AuthorityService authorityService;

    @Autowired
    public AuthorityController(AuthorityService authorityService)
    {
        this.authorityService = authorityService;
    }

    @GetMapping("/authorities/all")
    public ResponseEntity<List<Authority>> getAllAuthorities()
    {
        return new ResponseEntity<>(this.authorityService.getAll(), HttpStatus.OK);
    }
}
