package com.secure.secure_back_end.dto.user.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLockAccount
{
    @NotNull
    @Length(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
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
