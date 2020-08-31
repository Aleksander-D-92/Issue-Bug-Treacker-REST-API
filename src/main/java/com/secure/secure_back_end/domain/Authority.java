package com.secure.secure_back_end.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "authorities")
public class Authority implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorityId;
    @Column(unique = true, nullable = false)
    private String authority;
    @Column(unique = true, nullable = false)
    private Integer authorityLevel;

    public Authority()
    {
    }

    public Integer getAuthorityLevel()
    {
        return authorityLevel;
    }

    public void setAuthorityLevel(Integer authorityLevel)
    {
        this.authorityLevel = authorityLevel;
    }

    @Override
    public String getAuthority()
    {
        return this.authority;
    }

    public Long getAuthorityId()
    {
        return authorityId;
    }

    public void setAuthorityId(Long id)
    {
        this.authorityId = id;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}
