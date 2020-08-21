package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.ticket.binding.TicketCreationForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketViewModel;
import com.secure.secure_back_end.repositories.ProjectRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.repositories.UserRepository;
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
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, UserRepository userRepository, ProjectRepository projectRepository, ModelMapper modelMapper)
    {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }

    public void createTicket(TicketCreationForm form)
    {

        Project project = this.projectRepository.findById(form.getProjectId()).orElse(null);
        User submitter = this.userRepository.findById(form.getSubmitterId()).orElse(null);
        Ticket ticket = this.modelMapper.map(form, Ticket.class);
        ticket.setId(null);
        ticket.setCreationDate(new Date());
        ticket.setProject(project);
        ticket.setSubmitter(submitter);
        ticket.setStatus(INITIAL_STATUS);
        this.ticketRepository.save(ticket);
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
