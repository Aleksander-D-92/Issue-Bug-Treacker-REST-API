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

import java.util.Date;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>
{

    @Modifying
    @Transactional
    @Query(value = "insert into tickets (title, description, creation_date, category, priority, status, project_id, submitter_id) values (:title, :description, :creation_date, :category, :priority, :status, :project_id, :submitter_id)", nativeQuery = true)
    void submitTicket(@Param(value = "title") String title, @Param(value = "description") String description,
                      @Param(value = "creation_date") Date creation_date, @Param(value = "category") String category,
                      @Param(value = "priority") String priority, @Param(value = "status") String status,
                      @Param(value = "project_id") long project_id, @Param(value = "submitter_id") long submitter_id);

    @Query("select t from tickets as t where t.project.id =:projectId")
    List<Ticket> findAllByProjectId(@Param(value = "projectId") long projectId);

    @Query("select t from tickets as t where t.submitter.id =:submitterId")
    List<Ticket> findAllBySubmitterId(@Param(value = "submitterId") long submitterId);

    @Modifying
    @Transactional
    @Query("update tickets as t set t.title=:title, t.description=:description,t.category=:category,t.priority=:priority, t.status=:status ,t.assignedDeveloper.id=:assignedDeveloperId where t.id=:id")
    void updateTicketManager(@Param(value = "title") String title, @Param(value = "description") String description,
                             @Param(value = "category") Category category, @Param(value = "priority") Priority priority,
                             @Param(value = "status") Status status, @Param(value = "assignedDeveloperId") long assignedDeveloperId,
                             @Param(value = "id") long id);

    @Modifying
    @Transactional
    @Query("update tickets as t set t.title=:title, t.description=:description, t.category=:category, t.priority=:priority where t.id=:id")
    void updateTicketDev(@Param(value = "title") String title, @Param(value = "description") String description,
                         @Param(value = "category") Category category, @Param(value = "priority") Priority priority,
                         @Param(value = "id") long id);
}
