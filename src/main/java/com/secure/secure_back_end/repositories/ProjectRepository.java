package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>
{
    List<Project> findByProjectManager(User user);

    @Modifying
    @Transactional
    @Query("update projects as p set p.title=:title, p.description=:description where p.id=:id")
    void editProject(@Param("id") Long id, @Param("title") String title, @Param("description") String description);
}
