package com.secure.secure_back_end.dto.ticket.view;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;

import java.util.Date;

public class TicketViewModel
{
    private long id;
    private long projectId;
    private long submitterId;
    private String title;
    private String projectTitle;
    private String submitterName;
    private String assignedDeveloper;
    private Priority priority;
    private Status status;
    private Category category;
    private Date creationDate;

    public TicketViewModel()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(long projectId)
    {
        this.projectId = projectId;
    }

    public long getSubmitterId()
    {
        return submitterId;
    }

    public void setSubmitterId(long submitterId)
    {
        this.submitterId = submitterId;
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

    public String getAssignedDeveloper()
    {
        return assignedDeveloper;
    }

    public void setAssignedDeveloper(String assignedDeveloper)
    {
        this.assignedDeveloper = assignedDeveloper;
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

    public String getSubmitterName()
    {
        return submitterName;
    }

    public void setSubmitterName(String submitterName)
    {
        this.submitterName = submitterName;
    }
}
