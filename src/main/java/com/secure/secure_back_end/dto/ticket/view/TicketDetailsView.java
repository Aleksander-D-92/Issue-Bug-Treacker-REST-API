package com.secure.secure_back_end.dto.ticket.view;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;
import com.secure.secure_back_end.dto.project.view.ProjectView;
import com.secure.secure_back_end.dto.user.view.UserView;

import java.util.Date;

public class TicketDetailsView
{
    private Long ticketId;
    private String title;
    private Priority priority;
    private Status status;
    private Category category;
    private Date creationDate;
    private UserView submitter;
    private UserView assignedDeveloper;
    private ProjectView projectView;

    public TicketDetailsView()
    {
    }

    public Long getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(Long ticketId)
    {
        this.ticketId = ticketId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
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

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public UserView getSubmitter()
    {
        return submitter;
    }

    public void setSubmitter(UserView submitter)
    {
        this.submitter = submitter;
    }

    public UserView getAssignedDeveloper()
    {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(UserView assignedDeveloper)
    {
        this.assignedDeveloper = assignedDeveloper;
    }

    public ProjectView getProjectView()
    {
        return projectView;
    }

    public void setProjectView(ProjectView projectView)
    {
        this.projectView = projectView;
    }
}
