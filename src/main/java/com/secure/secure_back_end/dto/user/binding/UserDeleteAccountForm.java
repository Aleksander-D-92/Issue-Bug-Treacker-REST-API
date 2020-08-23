package com.secure.secure_back_end.dto.user.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserDeleteAccountForm
{
    @NotNull
    @Min(1)
    private Long userId;
    @NotNull
    @Length(min = 4, max = 15, message = "password must be between 4 and 15 symbols")
    private String password;

    public UserDeleteAccountForm()
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


}
