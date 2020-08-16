package com.secure.secure_back_end.services;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.repositories.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorityServiceImpl
{
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository)
    {
        this.authorityRepository = authorityRepository;
    }

    public List<Authority> getAll()
    {
        return this.authorityRepository.findAll();
    }
}
