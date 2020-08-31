package com.secure.secure_back_end.dto.user.view;

import com.secure.secure_back_end.domain.Authority;

import java.util.Date;

public class UserViewModel
{
    private Long userId;
    private String username;
    private Date registrationDate;
    boolean accountNonLocked;
    private Authority authority;

    public UserViewModel()
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

    public boolean isAccountNonLocked()
    {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked)
    {
        this.accountNonLocked = accountNonLocked;
    }

    public Authority getAuthority()
    {
        return authority;
    }

    public void setAuthority(Authority authority)
    {
        this.authority = authority;
    }
}
