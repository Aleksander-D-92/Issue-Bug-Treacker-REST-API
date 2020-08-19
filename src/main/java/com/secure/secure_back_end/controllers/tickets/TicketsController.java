package com.secure.secure_back_end.controllers.tickets;

import com.secure.secure_back_end.dto.ticket.TicketCreationForm;
import com.secure.secure_back_end.services.implementations.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TicketsController
{
    private final TicketServiceImpl ticketService;

    @Autowired
    public TicketsController(TicketServiceImpl ticketService)
    {
        this.ticketService = ticketService;
    }

    @PostMapping("/tickets/submit-ticket")
    public void submitTicket(@Valid @RequestBody TicketCreationForm form)
    {
        this.ticketService.createTicket(form);
    }
}
