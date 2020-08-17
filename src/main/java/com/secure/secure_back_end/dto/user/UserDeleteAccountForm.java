package com.secure.secure_back_end.dto.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDeleteAccountForm
{
    @NotNull
    @Size(min = 5, max = 30, message = "username must be between 4 and 30 symbols")
    private String username;
    @NotNull
    @Length(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
    private String password;

    public UserDeleteAccountForm()
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

    @Override
    public String toString()
    {
        return "UserDeleteForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
