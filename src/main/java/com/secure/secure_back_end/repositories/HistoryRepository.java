package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.History;
import com.secure.secure_back_end.domain.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>
{

    @EntityGraph(value = "ticketAssignedDeveloper")
    List<History> findAllByTicket(Ticket ticket);
}
