package com.secure.secure_back_end.controllers.tickets;

import com.secure.secure_back_end.dto.ticket.binding.TicketCreateForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketViewModel;
import com.secure.secure_back_end.services.implementations.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TicketController
{
    private final TicketServiceImpl ticketService;

    @Autowired
    public TicketController(TicketServiceImpl ticketService)
    {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets/get-all")
    public List<TicketViewModel> getAll()
    {
        return this.ticketService.getAllTickets();
    }

    @GetMapping("/tickets/get-by-submitter-id/{submitterId}")
    public List<TicketViewModel> getAllByUserId(@PathVariable(value = "submitterId") long submitterId)
    {
        return this.ticketService.getAllTicketsBySubmitterId(submitterId);
    }

    @GetMapping("/tickets/get-by-project-id/{projectId}")
    public List<TicketViewModel> getAllByProjectId(@PathVariable(value = "projectId") long projectId)
    {
        return this.ticketService.getAllTicketsByProjectId(projectId);
    }

    @PostMapping("/tickets/submit-ticket")
    public void submitTicket(@Valid @RequestBody TicketCreateForm form)
    {
        this.ticketService.submitTicket(form);
    }

    @PutMapping("/tickets/edit-ticket/manager")
    public void editTicket(@Valid @RequestBody TicketManagerEditForm form)
    {
        this.ticketService.editTicketManager(form);
    }

    @PutMapping("/tickets/edit-ticket/dev")
    public void editTicket(@Valid @RequestBody TicketDevEditForm form)
    {
        this.ticketService.editTicketDevs(form);
    }
}
