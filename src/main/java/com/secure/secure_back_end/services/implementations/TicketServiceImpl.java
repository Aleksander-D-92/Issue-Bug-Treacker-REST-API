package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.ticket.binding.TicketCreationForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketViewModel;
import com.secure.secure_back_end.repositories.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl
{
    private static final Status INITIAL_STATUS = Status.NEW;
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper)
    {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    public void submitTicket(TicketCreationForm form)
    {
        this.ticketRepository.submitTicket(
                form.getTitle(), form.getDescription(),
                new Date(), form.getCategory().toString(),
                form.getPriority().toString(), INITIAL_STATUS.toString(),
                form.getProjectId(), form.getSubmitterId());
    }

    public List<TicketViewModel> getAllTicketsByProjectId(long id)
    {
        List<Ticket> allByProjectId = this.ticketRepository.findAllByProjectId(id);
        return allByProjectId.stream().map(ticket ->
        {
            TicketViewModel newTicket = this.modelMapper.map(ticket, TicketViewModel.class);
            newTicket.setProjectId(ticket.getProject().getId());
            newTicket.setSubmitterId(ticket.getSubmitter().getId());
            return newTicket;
        }).collect(Collectors.toList());
    }

    public List<TicketViewModel> getAllTicketsBySubmitterId(long id)
    {
        List<Ticket> allByProjectId = this.ticketRepository.findAllBySubmitterId(id);
        return allByProjectId.stream().map(ticket ->
        {
            TicketViewModel newTicket = this.modelMapper.map(ticket, TicketViewModel.class);
            newTicket.setProjectId(ticket.getProject().getId());
            newTicket.setSubmitterId(ticket.getSubmitter().getId());
            return newTicket;
        }).collect(Collectors.toList());
    }

    public void editTicketManager(TicketManagerEditForm form)
    {
        this.ticketRepository.updateTicketManager(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(),
                form.getStatus(), form.getAssignedDeveloperId(),
                form.getTicketId());
    }


    public void editTicketDevs(TicketDevEditForm form)
    {
        this.ticketRepository.updateTicketDev(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(),
                form.getTicketId());
    }
}
