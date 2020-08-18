package com.secure.secure_back_end.domain;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;

import javax.persistence.*;

@Entity
public class History
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne(targetEntity = Ticket.class)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Priority getPriority()
    {
        return priority;
    }

    public void setPriority(Priority priority)
    {
        this.priority = priority;
    }

    public Ticket getTicket()
    {
        return ticket;
    }

    public void setTicket(Ticket ticket)
    {
        this.ticket = ticket;
    }
}
