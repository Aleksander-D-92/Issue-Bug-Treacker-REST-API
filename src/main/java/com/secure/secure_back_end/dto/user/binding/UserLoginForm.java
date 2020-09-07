package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.Pattern;

public class UserLoginForm
{

    @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}$",
            message = "username must be between 5 and 20 chars, can only include letters, numbers and \"_\"")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "password must be at least six characters, include at least one letter and at least one number")
    private String password;

    private boolean rememberMe;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean isRememberMe()
    {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString()
    {
        return "UserLoginForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isRememberMe=" + rememberMe +
                '}';
    }
}
