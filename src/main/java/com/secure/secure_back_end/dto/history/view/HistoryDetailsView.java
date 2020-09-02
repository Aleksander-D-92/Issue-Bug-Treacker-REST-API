package com.secure.secure_back_end.dto.history.view;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.ticket.view.TicketVIew;
import com.secure.secure_back_end.dto.user.view.UserView;

import java.util.Date;

public class HistoryDetailsView
{
    private Long historyId;
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Status status;
    private Date dateOfChange;
    private UserView assignedDeveloper;
    private TicketVIew ticket;

    public HistoryDetailsView()
    {
    }

    public Long getHistoryId()
    {
        return historyId;
    }

    public void setHistoryId(Long historyId)
    {
        this.historyId = historyId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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

    public UserView getAssignedDeveloper()
    {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(UserView assignedDeveloper)
    {
        this.assignedDeveloper = assignedDeveloper;
    }

    public TicketVIew getTicket()
    {
        return ticket;
    }

    public void setTicket(TicketVIew ticket)
    {
        this.ticket = ticket;
    }
}
