package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.History;
import com.secure.secure_back_end.domain.Project;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.project.view.ProjectView;
import com.secure.secure_back_end.dto.ticket.binding.TicketCreateForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketDevEditForm;
import com.secure.secure_back_end.dto.ticket.binding.TicketManagerEditForm;
import com.secure.secure_back_end.dto.ticket.view.TicketDetailsView;
import com.secure.secure_back_end.dto.user.view.UserView;
import com.secure.secure_back_end.repositories.HistoryRepository;
import com.secure.secure_back_end.repositories.ProjectRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import com.secure.secure_back_end.services.interfaces.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService
{
    private static final Status INITIAL_STATUS = Status.UNASSIGNED;
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

    @Override
    public void submitTicket(TicketCreateForm form, Long projectId)
    {
        Ticket map = this.modelMapper.map(form, Ticket.class);
        Project one = this.projectRepository.getOne(projectId);
        User one1 = this.userRepository.getOne(form.getSubmitterId());
        map.setStatus(INITIAL_STATUS);
        map.setCreationDate(new Date());
        map.setProject(one);
        map.setSubmitter(one1);
        this.ticketRepository.save(map);
    }

    @Override
    public List<TicketDetailsView> findAll()
    {
        List<Ticket> allBy = this.ticketRepository.findAllBy();
        return map(allBy);
    }

    @Override
    public List<TicketDetailsView> findAllBySubmitter(long id)
    {
        User submitter = this.userRepository.getOne(id);
        List<Ticket> withProject = this.ticketRepository.findAllBySubmitter(submitter);
        return map(withProject);
    }

    @Override
    public List<TicketDetailsView> findAllByProject(long id)
    {
        Project project = this.projectRepository.getOne(id);
        List<Ticket> tickets = this.ticketRepository.findAllByProject(project);
        return map(tickets);
    }

    @Override
    public List<TicketDetailsView> findAllByMangerId(Long id)
    {
        List<Long> ids = this.ticketRepository.getAllProjectIdsByMangerId(id);
        List<Project> projects = new ArrayList<>();
        ids.forEach(e -> projects.add(this.projectRepository.getOne(e)));
        List<Ticket> tickets = this.ticketRepository.findAllByProjectIn(projects);
        return map(tickets);
    }

    @Override
    public List<TicketDetailsView> findAllByAssignedDeveloperId(Long id)
    {
        User assignedDev = this.userRepository.getOne(id);
        List<Ticket> tickets = this.ticketRepository.findAllByAssignedDeveloper(assignedDev);
        return map(tickets);
    }

    @Override
    public List<TicketDetailsView> findOne(Long id)
    {
        Ticket byTicketId = this.ticketRepository.findByTicketId(id);
        return map(Collections.singletonList(byTicketId));
    }

    @Override
    public void editTicketManager(TicketManagerEditForm form, Long ticketId)
    {
        updateHistory(ticketId);
        this.ticketRepository.updateTicketManager(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(),
                form.getStatus(), form.getAssignedDeveloperId(),
                ticketId);
    }


    @Override
    public void editTicketDevs(TicketDevEditForm form, @Min(1) Long ticketId)
    {
        Status status;
        if (form.getResolved())
        {
            status = Status.RESOLVED;
        } else
        {
            status = Status.IN_PROGRESS;
        }
        updateHistory(ticketId);
        this.ticketRepository.updateTicketDev(
                form.getTitle(), form.getDescription(),
                form.getCategory(), form.getPriority(),
                status, ticketId);
    }

    private void updateHistory(Long ticketId)
    {
        Ticket ticket = this.ticketRepository.findById(ticketId).orElse(null);
        History history = this.modelMapper.map(ticket, History.class);
        history.setDateOfChange(new Date());
        history.setTicket(ticket);
        history.setHistoryId(null);
        this.historyRepository.save(history);
    }

    private List<TicketDetailsView> map(List<Ticket> allBySubmitter)
    {
        return allBySubmitter.stream()
                .map(ticket ->
                {
                    TicketDetailsView map = this.modelMapper.map(ticket, TicketDetailsView.class);
                    UserView submitter = this.modelMapper.map(ticket.getSubmitter(), UserView.class);
                    ProjectView projectView = this.modelMapper.map(ticket.getProject(), ProjectView.class);
                    map.setSubmitter(submitter);
                    map.setProject(projectView);
                    if (ticket.getAssignedDeveloper() != null)
                    {
                        UserView assignedDev = this.modelMapper.map(ticket.getAssignedDeveloper(), UserView.class);
                        map.setAssignedDeveloper(assignedDev);
                    }
                    return map;
                })
                .collect(Collectors.toList());
    }
}
