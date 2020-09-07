package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.Pattern;

public class UserChangePasswordForm
{

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "password must be at least six characters, include at least one letter and at least one number")
    private String oldPassword;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "password must be at least six characters, include at least one letter and at least one number")
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
