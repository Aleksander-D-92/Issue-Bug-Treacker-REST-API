package com.secure.secure_back_end.controllers.users;

import com.secure.secure_back_end.configuration.security.jwt.JWTToken;
import com.secure.secure_back_end.configuration.security.jwt.TokenProvider;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.user.UserLoginForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserJWTController
{

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager)
    {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/users/authenticate")
    @ApiOperation(value = "JWT", notes = "Builds an authentication token with username, userId, grantedAuthorities, exp date")
    public ResponseEntity authorize(@Valid @RequestBody UserLoginForm userLoginForm)
    {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userLoginForm.getUsername(), userLoginForm.getPassword());
        Authentication authentication;
        User user;
        String jwt;
        //if invalid credentials
        try
        {
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException ae)
        {
            return new ResponseEntity<>("Invalid Username or Password", HttpStatus.UNAUTHORIZED);
        }
        /*
         *if we are trying to authenticate IM MEMORY users, we will get ClassCastException
         *so instead we will call createToken() with no id = -1 and Authentication authentication instead of User
         */
        try
        {
            user = (User) authentication.getPrincipal();
        } catch (ClassCastException classCastException)
        {
            jwt = tokenProvider.createToken(authentication, userLoginForm.isRememberMe(), -1);
            return new ResponseEntity<>(new JWTToken(jwt), HttpStatus.OK);
        }
        jwt = tokenProvider.createToken(authentication, userLoginForm.isRememberMe(), user.getId());
        SecurityContextHolder.getContext().setAuthentication(authentication);//?? we don't need this line
        return new ResponseEntity<>(new JWTToken(jwt), HttpStatus.OK);
    }
}
