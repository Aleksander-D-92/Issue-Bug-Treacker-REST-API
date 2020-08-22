package com.secure.secure_back_end.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "authorities")
public class Authority implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setAuthority(String authority)
    {
        this.authority = authority;
    }
}
