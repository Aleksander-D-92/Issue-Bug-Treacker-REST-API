package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);

    List<User> findByIdIn(List<Long> userIds);
}
