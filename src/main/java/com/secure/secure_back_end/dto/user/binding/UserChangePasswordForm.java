package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserChangePasswordForm
{
    @NotNull
    @Size(min = 5, max = 30, message = "username must be between 4 and 30 symbols")
    private String username;
    @NotNull
    @Size(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
    private String password;
    @NotNull
    @Size(min = 4, max = 15, message = "newPassword must be between 4 and 15 symbols")
    private String newPassword;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public UserChangePasswordForm()
    {
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }
}
