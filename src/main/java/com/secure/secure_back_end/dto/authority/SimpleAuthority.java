package com.secure.secure_back_end.dto.authority;

import javax.validation.constraints.NotNull;

public class SimpleAuthority
{
    @NotNull
    private String authority;

    public SimpleAuthority()
    {
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
        return "SimpleAuthority{" +
                "authority='" + authority + '\'' +
                '}';
    }
}
