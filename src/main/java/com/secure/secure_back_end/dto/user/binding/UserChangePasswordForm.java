package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserChangePasswordForm
{
    @NotNull
    @Min(1)
    private Long userId;
    @NotNull
    @Size(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
    private String password;
    @NotNull
    @Size(min = 4, max = 15, message = "newPassword must be between 4 and 15 symbols")
    private String newPassword;



    public UserChangePasswordForm()
    {
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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
