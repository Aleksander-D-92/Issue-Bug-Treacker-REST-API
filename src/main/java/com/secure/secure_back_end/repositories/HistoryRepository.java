package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long>
{
    @Query("select th from tickets_history as th left join fetch th.assignedDeveloper where th.ticket.ticketId=:ticket_id")
    List<History> getHistoryForTicket(@Param("ticket_id") Long ticketId);
}
