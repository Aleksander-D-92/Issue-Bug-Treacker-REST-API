package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.ticket.binding.TicketCreateForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketQaEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketDetailsView;

import javax.validation.constraints.Min;
import java.util.List;

public interface TicketService
{
    void submitTicket(TicketCreateForm form, Long projectId);

    List<TicketDetailsView> findAll();

    List<TicketDetailsView> findOne(Long id);

    List<TicketDetailsView> findAllBySubmitter(long id);

    List<TicketDetailsView> findAllByProject(long id);

    List<TicketDetailsView> findAllByMangerId(Long id);

    List<TicketDetailsView> findAllByAssignedDeveloperId(Long id);

    void editTicketManager(TicketManagerEditForm form, Long ticketId);

    void editTicketDevs(TicketDevEditForm form, @Min(1) Long ticketId);

    void editTicketQa(TicketQaEditForm form, Long ticketId);
}
