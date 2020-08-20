package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.ticket.binding_models.TicketCreationForm;
import com.secure.secure_back_end.dto.ticket.view_models.TicketViewModel;
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
        ticket.setCreationDate(new Date());
        this.ticketRepository.saveTicket(
                ticket.getTitle(), ticket.getDescription(), ticket.getCategory().toString(),
                ticket.getPriority().toString(), form.getProjectId(), form.getSubmitterId(),
                ticket.getCreationDate());
    }

    public List<TicketViewModel> getAllTicketsByProjectId(long id)
    {
        Project project = this.projectRepository.findById(id).orElse(null);
        List<Ticket> allByProject = this.ticketRepository.findAllByProject(project);
        return allByProject.stream().map(ticket ->
        {
            TicketViewModel mapped = this.modelMapper.map(ticket, TicketViewModel.class);
            mapped.setProjectName(ticket.getProject().getTitle());
            return mapped;
        }).collect(Collectors.toList());
    }

    public List<TicketViewModel> getAllTicketsByUserId(long id)
    {
        User user = this.userRepository.findById(id).orElse(null);
        List<Ticket> allByProject = this.ticketRepository.findAllBySubmitter(user);
        return allByProject.stream().map(ticket ->
        {
            TicketViewModel ticketViewModel = this.modelMapper.map(ticket, TicketViewModel.class);
            ticketViewModel.setProjectName(ticket.getProject().getTitle());
            return ticketViewModel;
        }).collect(Collectors.toList());
    }

}
