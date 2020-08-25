package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.repositories.AuthorityRepository;
import com.secure.secure_back_end.services.interfaces.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImpl implements AuthorityService
{
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository)
    {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<Authority> getAll()
    {
        return this.authorityRepository.findAll();
    }
}
