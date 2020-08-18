package com.secure.secure_back_end.dto.authority;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserChangeAuthorityForm
{
    @NotNull
    @Min(value = -1, message = "userId is invalid")
    private long userId;
    @NotNull(message = "authority is null")
    private String authority;

    public UserChangeAuthorityForm()
    {
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
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
        return "UserChangeAuthorityForm{" +
                "id=" + userId +
                ", authority='" + authority + '\'' +
                '}';
    }
}
