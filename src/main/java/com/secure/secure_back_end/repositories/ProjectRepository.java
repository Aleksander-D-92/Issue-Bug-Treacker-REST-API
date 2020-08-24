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

    @Query("select p from projects p join fetch p.assignedDevelopers where p.id=:project_id")
    Project getAssignedDevelopers(@Param("project_id") long id);

    @Modifying
    @Transactional
    @Query("update projects as p set p.title=:title, p.description=:description where p.id=:id")
    void editProject(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "insert into projects_developers (project_id, user_id) values (:project_id, :user_id)", nativeQuery = true)
    void addDevelopersToProject(@Param("project_id") Long projectId, @Param("user_id") Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from projects_developers where project_id=:project_id and user_id=:user_id", nativeQuery = true)
    void removeDevelopersFromProject(@Param("project_id") Long projectId, @Param("user_id") Long userId);
}
