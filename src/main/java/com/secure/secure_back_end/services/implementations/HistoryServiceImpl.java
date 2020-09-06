package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.dto.history.view.HistoryDetailsView;
import com.secure.secure_back_end.dto.ticket.view.TicketVIew;
import com.secure.secure_back_end.dto.user.view.UserView;
import com.secure.secure_back_end.repositories.HistoryRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.services.interfaces.HistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService
{
    private final HistoryRepository historyRepository;
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public HistoryServiceImpl(HistoryRepository historyRepository, TicketRepository ticketRepository, ModelMapper modelMapper)
    {
        this.historyRepository = historyRepository;
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<HistoryDetailsView> findAllByTicket(Long ticketId)
    {
        Ticket ticket = this.ticketRepository.getOne(ticketId);
        return this.historyRepository.findAllByTicket(ticket).stream().sorted((o1, o2) -> o2.getDateOfChange().compareTo(o1.getDateOfChange()))
                .map(history ->
                {
                    HistoryDetailsView map = this.modelMapper.map(history, HistoryDetailsView.class);
                    TicketVIew ticket1 = this.modelMapper.map(history.getTicket(), TicketVIew.class);
                    map.setTicket(ticket1);
                    if (history.getAssignedDeveloper() != null)
                    {
                        UserView assignedDev = this.modelMapper.map(history.getAssignedDeveloper(), UserView.class);
                        map.setAssignedDeveloper(assignedDev);
                    }
                    return map;
                }).collect(Collectors.toList());
    }
}
