package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);

    @Query("select u from User u join fetch u.authorities a where u.id=:user_id")
    User selectUserDetails(@Param("user_id") Long id);

    List<User> findByIdIn(List<Long> userIds);

    List<User> findByAuthorities(Authority authority);
}
