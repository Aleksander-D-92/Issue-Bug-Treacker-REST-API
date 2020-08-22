package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.History;
import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.ticket.binding.TicketCreationForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketViewModel;
import com.secure.secure_back_end.repositories.HistoryRepository;
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
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ProjectRepository projectRepository, UserRepository userRepository, HistoryRepository historyRepository, ModelMapper modelMapper)
    {
        this.ticketRepository = ticketRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.modelMapper = modelMapper;
    }

    public void submitTicket(TicketCreationForm form)
    {
        Ticket map = this.modelMapper.map(form, Ticket.class);
        Project one = this.projectRepository.getOne(form.getProjectId());
        User one1 = this.userRepository.getOne(form.getSubmitterId());
        map.setStatus(INITIAL_STATUS);
        map.setCreationDate(new Date());
        map.setProject(one);
        map.setSubmitter(one1);
        this.ticketRepository.save(map);
    }

    public List<TicketViewModel> getAllTicketsByProjectId(long id)
    {
        Project project = this.projectRepository.getOne(id);
        List<Ticket> allByProject = this.ticketRepository.findAllByProject(project);
        return getMapped(allByProject);
    }

    public List<TicketViewModel> getAllTicketsBySubmitterId(long id)
    {
        User submitter = this.userRepository.getOne(id);
        List<Ticket> allBySubmitter = this.ticketRepository.findAllBySubmitter(submitter);
        return getMapped(allBySubmitter);
    }

    private List<TicketViewModel> getMapped(List<Ticket> allBySubmitter)
    {
        return allBySubmitter.stream().map(ticket ->
        {
            TicketViewModel map = this.modelMapper.map(ticket, TicketViewModel.class);
            map.setSubmitterId(ticket.getSubmitter().getId());
            map.setSubmitterName(ticket.getSubmitter().getUsername());
            map.setProjectId(ticket.getProject().getId());
            map.setProjectTitle(ticket.getProject().getTitle());
            return map;
        }).collect(Collectors.toList());
    }

    public void editTicketManager(TicketManagerEditForm form)
    {
        updateHistory(form.getTicketId());
        this.ticketRepository.updateTicketManager(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(),
                form.getStatus(), form.getAssignedDeveloperId(),
                form.getTicketId());
    }


    public void editTicketDevs(TicketDevEditForm form)
    {
        updateHistory(form.getTicketId());
        this.ticketRepository.updateTicketDev(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(),
                form.getTicketId());
    }

    private void updateHistory(Long ticketId)
    {
        Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
        History history = this.modelMapper.map(ticket, History.class);
        history.setDateOfChange(new Date());
        history.setTicket(ticket);
        history.setId(null);
        this.historyRepository.save(history);
    }


}
