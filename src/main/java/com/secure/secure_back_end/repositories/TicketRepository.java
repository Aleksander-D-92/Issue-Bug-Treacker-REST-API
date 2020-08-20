package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>
{

    List<Ticket> findAllBySubmitter(User user);

    List<Ticket> findAllByProject(Project project);

    @Query("select t from tickets as t where t.project.id =:projectId")
    List<Ticket> findAllByProjectId(@Param(value = "projectId") long projectId);
}
