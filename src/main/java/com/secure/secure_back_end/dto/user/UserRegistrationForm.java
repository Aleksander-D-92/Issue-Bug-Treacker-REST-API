package com.secure.secure_back_end.dto.user;


import com.secure.secure_back_end.configuration.exceptions.PasswordMissMatchException;
import com.secure.secure_back_end.dto.user.confirm_password_validation.ValidateConfirmPassword;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ValidateConfirmPassword
public class UserRegistrationForm
{
    @NotNull
    @Size(min = 5, max = 30, message = "username must be between 4 and 30 symbols")
    private String username;
    @NotNull
    @Size(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
    private String password;
    @NotNull
    @Size(min = 4, max = 15, message = "confirmPassword must be between 4 and 15 symbols")
    private String confirmPassword;
    private String authority;

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

    public void setPassword(String password) throws PasswordMissMatchException
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

    public String getAuthority()
    {
        return authority;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }

    @Override
    public String toString()
    {
        return "UserRegistrationForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", role='" + authority + '\'' +
                '}';
    }
}
