package com.secure.secure_back_end.controllers.tickets;

import com.secure.secure_back_end.dto.ticket.binding.TicketCreateForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketDetailsView;
import com.secure.secure_back_end.services.interfaces.TicketService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
public class TicketController
{
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService)
    {
        this.ticketService = ticketService;
    }

    @GetMapping("/tickets")
    @ApiOperation("action must equal \"all\" | \"by-project\"| \"by-submitter\" | \"by-project-manager\" |\"by-assigned-developer\". If action equals \"by-project\" |\"by-submitter\" you must provide an id.\n Example GET /tickets?action=by-project&id=12")
    public List<TicketDetailsView> getTickets(@RequestParam("action") @Pattern(regexp = "^all$|^by-project$|^by-submitter$|^by-project-manager$|by-assigned-developer$") String action,
                                              @RequestParam(value = "id", required = false) @Min(1) Long id)
    {
        switch (action)
        {
            case "all":
                return this.ticketService.findAll();
            case "by-project":
                return this.ticketService.findAllByProject(id);
            case "by-submitter":
                return this.ticketService.findAllBySubmitter(id);
            case "by-project-manager":
                return this.ticketService.findAllByMangerId(id);
            case "by-assigned-developer":
                return this.ticketService.findAllByAssignedDeveloperId(id);
            default:
                return new ArrayList<>();
        }
    }

    @PostMapping("/tickets/{projectId}")
    @ApiOperation(value = "creates a new ticket")
    public void submitTicket(@Valid @RequestBody TicketCreateForm form,
                             @PathVariable(value = "projectId") @Min(1) Long projectId)
    {
        this.ticketService.submitTicket(form, projectId);
    }

    @PutMapping("/tickets/{ticketId}/manager")
    @ApiOperation(value = "used to edit tickets by project managers")
    public void editTicket(@Valid @RequestBody TicketManagerEditForm form,
                           @PathVariable("ticketId") @Min(1) Long ticketId)
    {
        this.ticketService.editTicketManager(form, ticketId);
    }

    @PutMapping("/tickets/{ticketId}/developer")
    @ApiOperation(value = "used to edit tickets by developers and submitters")
    public void editTicket(@Valid @RequestBody TicketDevEditForm form,
                           @PathVariable("ticketId") @Min(1) Long ticketId)
    {
        this.ticketService.editTicketDevs(form, ticketId);
    }
}
