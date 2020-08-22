package com.secure.secure_back_end.dto.user.view;

import com.secure.secure_back_end.domain.Authority;

import java.util.Date;

public class UserViewModel
{
    private long id;
    private String username;
    private Date registrationDate;
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
