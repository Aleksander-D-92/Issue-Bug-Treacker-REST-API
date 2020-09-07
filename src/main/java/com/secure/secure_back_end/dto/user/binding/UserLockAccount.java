package com.secure.secure_back_end.dto.user.binding;

import javax.validation.constraints.Pattern;

public class UserLockAccount
{
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "password minimum must be at least six characters, at least one letter and one number")
    private String password;

    public UserLockAccount()
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


}
