package com.secure.secure_back_end.dto.user.binding;

import com.secure.secure_back_end.dto.user.confirm_password_validation.ValidateConfirmPassword;

import javax.validation.constraints.Pattern;

@ValidateConfirmPassword
public class ManagerStaffForm
{
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}$",
            message = "username must be between 5 and 20 chars, can only include letters, numbers and \"_\"")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "password must be at least six characters, include at least one letter and at least one number")
    private String password;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "confirm password must be at least six characters, include at least one letter and at least one number")
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
