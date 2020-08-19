package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>
{

}
