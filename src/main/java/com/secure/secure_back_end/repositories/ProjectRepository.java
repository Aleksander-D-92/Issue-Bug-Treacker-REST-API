package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Project;
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
public interface ProjectRepository extends JpaRepository<Project, Long>
{

    @EntityGraph(value = "fetchProjectManager")
    Project findByProjectId(Long projectId);

    @EntityGraph(value = "fetchProjectManager")
    List<Project> findAllByProjectIdIn(List<Long> ids);

    @EntityGraph(value = "fetchProjectManager")
    List<Project> findAllBy();

    @EntityGraph(value = "fetchProjectManager")
    List<Project> findAllByProjectManager(User projectManager);

    @Query(value = "select pq.project_id from destroy_bugs.projects_qa as pq where pq.qa_id=:qa_id", nativeQuery = true)
    List<Long> findProjectIdsThatIncludeQA(@Param("qa_id") Long id);

    @Modifying
    @Transactional
    @Query("update projects as p set p.title=:title, p.description=:description where p.projectId=:id")
    void editProject(@Param("id") Long id, @Param("title") String title, @Param("description") String description);

    @Modifying
    @Transactional
    @Query(value = "insert into destroy_bugs.projects_qa (project_id, qa_id) values (:project_id, :qa_id)", nativeQuery = true)
    void addQaToProject(@Param("project_id") Long projectId, @Param("qa_id") Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from destroy_bugs.projects_qa where project_id=:project_id and qa_id in :qa_ids", nativeQuery = true)
    void removeQaFromProject(@Param("project_id") Long projectId, @Param("qa_ids") List<Long> ids);

    //todo getAvailableQaToAssign
    @Query(value = "select u.user_id from destroy_bugs.users u join destroy_bugs.users_authorities ua on u.user_id = ua.user_id where u.manager_id =:manager_id and ua.authority_id = 1", nativeQuery = true)
    List<Long> getQaIdsForManger(@Param("manager_id") Long id);

    @Query(value = "select u.user_id from destroy_bugs.users u join destroy_bugs.projects_qa pq on u.user_id = pq.qa_id where pq.project_id =:project_id", nativeQuery = true)
    List<Long> getQaIdsForProject(@Param("project_id") Long id);
}
