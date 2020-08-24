package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserChangePasswordForm
{

    @NotNull
    @Size(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
    private String oldPassword;
    @NotNull
    @Size(min = 4, max = 15, message = "newPassword must be between 4 and 15 symbols")
    private String newPassword;



    public UserChangePasswordForm()
    {
    }



    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
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
