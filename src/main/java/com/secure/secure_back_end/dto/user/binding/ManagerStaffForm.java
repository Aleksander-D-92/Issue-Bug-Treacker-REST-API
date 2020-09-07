package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.Pattern;

public class ManagerStaffForm
{
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$",
            message = "username must be between 5 and 20 chars, can only include numbers and chars")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "password minimum must be at least six characters, at least one letter and one number")
    private String password;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "confirm password must be at least six characters, at least one letter and one number")
    private String confirmPassword;

    public ManagerStaffForm()
    {
    }

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

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }
}
