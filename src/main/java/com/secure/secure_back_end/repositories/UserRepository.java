package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Authority;
import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    @EntityGraph(value = "fetchAuthorities")
    User findByUsername(String username);

    @EntityGraph(value = "fetchAuthorities")
    User findByUserId(Long id);

    @EntityGraph(value = "fetchAuthorities")
    List<User> findAllBy();

    @EntityGraph(value = "fetchAuthorities")
    List<User> findAllByAuthoritiesContaining(Authority authority);

    @EntityGraph(value = "fetchAuthorities")
    List<User> findAllByUserIdIn(List<Long> ids);

    @Query("select u from User u join fetch u.staff s join fetch s.authorities where u.userId=:manager_id")
    User findStaff(@Param("manager_id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from users_authorities where user_id=:user_id", nativeQuery = true)
    void deleteAuthority(@Param("user_id") Long userId);

    @Modifying
    @Transactional
    @Query(value = "insert into users_authorities (user_id, authority_id) values (:user_id,:authority_id)", nativeQuery = true)
    void insertAuthority(@Param("user_id") Long userId, @Param("authority_id") Long authorityId);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.accountNonLocked=:lock where u.userId=:user_id")
    void setIsAccountNonLocked(@Param("user_id") Long id, @Param("lock") boolean lock);
}
