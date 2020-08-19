package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.ticket.TicketCreationForm;
import com.secure.secure_back_end.repositories.ProjectRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketServiceImpl
{
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

        Ticket ticket = this.modelMapper.map(form, Ticket.class, "");
        ticket.setId(null);
        User user = this.userRepository.findById(form.getSubmitterId()).orElse(null);
        Project project = this.projectRepository.findById(form.getProjectId()).orElse(null);
        ticket.setProject(project);
        ticket.setSubmitter(user);
        ticket.setCreationDate(new Date());
        this.ticketRepository.save(ticket);
    }
}
