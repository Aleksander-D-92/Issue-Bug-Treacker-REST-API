package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.ticket.binding.TicketCreateForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketViewModel;

import javax.validation.constraints.Min;
import java.util.List;

public interface TicketService
{
    void submitTicket(TicketCreateForm form, Long projectId);

    List<TicketViewModel> getAllTickets();

    List<TicketViewModel> getAllTicketsBySubmitterId(long id);

    List<TicketViewModel> getAllTicketsByProjectId(long id);

    List<TicketViewModel> getAllTicketsByMangerId(Long id);

    void editTicketManager(TicketManagerEditForm form, Long ticketId);

    void editTicketDevs(TicketDevEditForm form, @Min(1) Long ticketId);
}
