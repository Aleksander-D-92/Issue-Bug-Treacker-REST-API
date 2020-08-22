package com.secure.secure_back_end.dto.user.view;

import com.secure.secure_back_end.domain.Authority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserViewModel
{
    @NotNull(message = "id must not be null")
    private long id;
    @NotNull(message = "message must not be null")
    @Size(min = 5, max = 30, message = "must be between 4 and 30 symbols")
    private String username;

    private Date registrationDate;

    @NotNull
    private Authority authority;
    public UserViewModel()
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

    public Date getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate)
    {
        this.registrationDate = registrationDate;
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
