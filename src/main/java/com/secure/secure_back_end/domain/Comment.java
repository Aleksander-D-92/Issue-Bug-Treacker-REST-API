package com.secure.secure_back_end.domain;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "comments")
public class Comment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String value;
    @Column
    private Date creationDate;
    @ManyToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Comment()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
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
