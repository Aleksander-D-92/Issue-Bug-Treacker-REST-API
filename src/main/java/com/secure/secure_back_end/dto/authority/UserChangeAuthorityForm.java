package com.secure.secure_back_end.dto.authority;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UserChangeAuthorityForm
{
    @NotNull
    @Min(-1)
    private long userId;
    @NotNull
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
