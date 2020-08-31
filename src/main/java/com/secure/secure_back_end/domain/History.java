package com.secure.secure_back_end.domain;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tickets_history")
public class History
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;
    @Column()
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column
    private Date dateOfChange;
    @ManyToOne(targetEntity = Ticket.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    private Ticket ticket;
    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_developer_id", referencedColumnName = "userId")
    private User assignedDeveloper;

    public History()
    {
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Long getHistoryId()
    {
        return historyId;
    }

    public void setHistoryId(Long id)
    {
        this.historyId = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Date getDateOfChange()
    {
        return dateOfChange;
    }

    public void setDateOfChange(Date dateOfChange)
    {
        this.dateOfChange = dateOfChange;
    }

    public Ticket getTicket()
    {
        return ticket;
    }

    public void setTicket(Ticket ticket)
    {
        this.ticket = ticket;
    }

    public User getAssignedDeveloper()
    {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(User assignedDeveloper)
    {
        this.assignedDeveloper = assignedDeveloper;
    }
}
