package com.secure.secure_back_end.dto.user;

import com.secure.secure_back_end.domain.Authority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserAuthorityDetails
{
    @NotNull
    private long id;
    @NotNull
    @Size(min = 5, max = 30, message = "must be between 4 and 30 symbols")
    private String username;
    @NotNull
    private Authority authority;

    public UserAuthorityDetails()
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

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public Authority getAuthority()
    {
        return authority;
    }

    public void setAuthority(Authority authority)
    {
        this.authority = authority;
    }

    @Override
    public String toString()
    {
        return "UserUpdateRoleForm{" +
                "username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
