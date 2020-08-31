package com.secure.secure_back_end.dto.ticket.view;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;

import java.util.Date;

public class TicketViewModel
{
    private Long ticketId;
    private Long projectId;
    private Long submitterId;
    private Long assignedDeveloperId;
    private String title;
    private String projectTitle;
    private String submitterName;
    private String assignedDeveloperName;
    private Priority priority;
    private Status status;
    private Category category;
    private Date creationDate;

    public TicketViewModel()
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

    public Long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getSubmitterId()
    {
        return submitterId;
    }

    public void setSubmitterId(Long submitterId)
    {
        this.submitterId = submitterId;
    }

    public Long getAssignedDeveloperId()
    {
        return assignedDeveloperId;
    }

    public void setAssignedDeveloperId(Long assignedDeveloperId)
    {
        this.assignedDeveloperId = assignedDeveloperId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getProjectTitle()
    {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle)
    {
        this.projectTitle = projectTitle;
    }

    public String getSubmitterName()
    {
        return submitterName;
    }

    public void setSubmitterName(String submitterName)
    {
        this.submitterName = submitterName;
    }

    public String getAssignedDeveloperName()
    {
        return assignedDeveloperName;
    }

    public void setAssignedDeveloperName(String assignedDeveloperName)
    {
        this.assignedDeveloperName = assignedDeveloperName;
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
}
