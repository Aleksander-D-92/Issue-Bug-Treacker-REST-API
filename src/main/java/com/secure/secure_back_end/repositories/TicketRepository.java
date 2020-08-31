package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>
{

    @Query("select t from tickets t join fetch t.project join fetch t.submitter left join fetch t.assignedDeveloper")
    List<Ticket> getAll();

    @Query("select t from tickets t join fetch t.project join fetch t.submitter left join fetch t.assignedDeveloper where t.project.id=:project_id")
    List<Ticket> getAllByProjectId(@Param("project_id") Long id);

    //todo finish getting all tickets by project manager id
    @Query(value = "select p.id from projects as p where p.project_manager_id=:project_manager_id", nativeQuery = true)
    List<Long> getAllProjectIdsByMangerId(@Param("project_manager_id") Long id);

    @Query("select t from tickets as t join fetch t.project join fetch t.submitter left join fetch t.assignedDeveloper where t.project.id in :project_ids")
    List<Ticket> getAllByProjectIdsIn(@Param("project_ids") List<Long> ids);

    @Query("select t from tickets t join fetch t.project join fetch t.submitter left join fetch t.assignedDeveloper where t.submitter.userId=:submitter_id")
    List<Ticket> getAllBySubmitterId(@Param("submitter_id") Long id);

    @Query("select t from tickets t join fetch t.project join fetch t.submitter join fetch t.assignedDeveloper where t.assignedDeveloper.userId=:assigned_developer_id")
    List<Ticket> getAllByAssignedDeveloperId(@Param("assigned_developer_id") Long id);

    @Modifying
    @Transactional
    @Query("update tickets as t set t.title=:title, t.description=:description,t.category=:category,t.priority=:priority, t.status=:status ,t.assignedDeveloper.userId=:assignedDeveloperId where t.id=:id")
    void updateTicketManager(@Param(value = "title") String title, @Param(value = "description") String description,
                             @Param(value = "category") Category category, @Param(value = "priority") Priority priority,
                             @Param(value = "status") Status status, @Param(value = "assignedDeveloperId") Long assignedDeveloperId,
                             @Param(value = "id") Long id);

    @Modifying
    @Transactional
    @Query("update tickets as t set t.title=:title, t.description=:description, t.category=:category, t.priority=:priority where t.id=:id")
    void updateTicketDev(@Param(value = "title") String title, @Param(value = "description") String description,
                         @Param(value = "category") Category category, @Param(value = "priority") Priority priority,
                         @Param(value = "id") Long id);
}
