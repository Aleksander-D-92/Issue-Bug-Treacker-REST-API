package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;
import org.springframework.data.jpa.repository.EntityGraph;
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
    @EntityGraph(value = "submitterProjectAssignedDev")
    List<Ticket> findAllBy();

    @EntityGraph(value = "submitterProjectAssignedDev")
    List<Ticket> findAllByProject(Project project);

    @EntityGraph(value = "submitterProjectAssignedDev")
    List<Ticket> findAllBySubmitter(User user);

    @EntityGraph(value = "submitterProjectAssignedDev")
    List<Ticket> findAllByAssignedDeveloper(User user);

    //we use this with the method below to find all tickets for a certain project manager.
    @EntityGraph(value = "submitterProjectAssignedDev")
    List<Ticket> findAllByProjectIn(List<Project> projects);

    @Query(value = "select p.project_id from projects as p where p.project_manager_id=:project_manager_id", nativeQuery = true)
    List<Long> getAllProjectIdsByMangerId(@Param("project_manager_id") Long id);

    @Modifying
    @Transactional
    @Query("update tickets as t set t.title=:title, t.description=:description,t.category=:category,t.priority=:priority, t.status=:status ,t.assignedDeveloper.userId=:assignedDeveloperId where t.ticketId=:id")
    void updateTicketManager(@Param(value = "title") String title, @Param(value = "description") String description,
                             @Param(value = "category") Category category, @Param(value = "priority") Priority priority,
                             @Param(value = "status") Status status, @Param(value = "assignedDeveloperId") Long assignedDeveloperId,
                             @Param(value = "id") Long id);

    @Modifying
    @Transactional
    @Query("update tickets as t set t.title=:title, t.description=:description, t.category=:category, t.priority=:priority where t.ticketId=:id")
    void updateTicketDev(@Param(value = "title") String title, @Param(value = "description") String description,
                         @Param(value = "category") Category category, @Param(value = "priority") Priority priority,
                         @Param(value = "id") Long id);

}
