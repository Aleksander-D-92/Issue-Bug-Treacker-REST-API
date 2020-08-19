package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.dto.ticket.TicketCreationForm;
import com.secure.secure_back_end.repositories.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl
{
    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper modelMapper)
    {
        this.ticketRepository = ticketRepository;
        this.modelMapper = modelMapper;
    }

    public void createTicket(TicketCreationForm form)
    {
        Ticket map = this.modelMapper.map(form, Ticket.class);
        map.setId(null);
    }
}
