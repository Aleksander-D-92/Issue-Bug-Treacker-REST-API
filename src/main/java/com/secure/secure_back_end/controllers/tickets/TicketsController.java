package com.secure.secure_back_end.controllers.tickets;

import com.secure.secure_back_end.dto.ticket.binding_models.TicketCreationForm;
import com.secure.secure_back_end.dto.ticket.view_models.TicketViewModel;
import com.secure.secure_back_end.services.implementations.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TicketsController
{
    private final TicketServiceImpl ticketService;

    @Autowired
    public TicketsController(TicketServiceImpl ticketService)
    {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets/get-by-user-id/{userId}")
    public List<TicketViewModel> getAllByUserId(@PathVariable(value = "userId") long userId)
    {
        return this.ticketService.getAllTicketsByUserId(userId);
    }

    @GetMapping("/tickets/get-by-project-id/{projectId}")
    public List<TicketViewModel> getAllByProjectId(@PathVariable(value = "projectId") long projectId)
    {
        return this.ticketService.getAllTicketsByProjectId(projectId);
    }

    @PostMapping("/tickets/submit-ticket")
    public void submitTicket(@Valid @RequestBody TicketCreationForm form)
    {
        this.ticketService.createTicket(form);
    }
}
