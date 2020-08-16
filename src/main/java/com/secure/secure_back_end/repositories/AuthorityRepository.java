package com.secure.secure_back_end.repositories;


import com.secure.secure_back_end.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long>
{
    Authority findByAuthority(String authority);
}

