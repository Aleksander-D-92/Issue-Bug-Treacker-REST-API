package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
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
    @Query(value = "insert into tickets (title,description,category,priority,project_id,submitter_id,creation_date,status) values (:title,:description,:category,:priority,:project_id,:submitter_id,:creation_date,:status)", nativeQuery = true)
    void saveTicket(@Param("title") String title, @Param("description") String description,
                    @Param("category") String category, @Param("priority") String priority,
                    @Param("project_id") long projectId, @Param("submitter_id") long submitterId,
                    @Param("creation_date") Date creationDate, @Param("status") String status);

    List<Ticket> findAllBySubmitter(User user);

    List<Ticket> findAllByProject(Project project);
}
