package com.secure.secure_back_end.dto.history.view;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;

import java.util.Date;

public class HistoryViewModel
{
    private Long historyId;
    private Long assignedDeveloperId;
    private String title;
    private String description;
    private String assignedDeveloperName;
    private Category category;
    private Priority priority;
    private Status status;
    private Date dateOfChange;

    public HistoryViewModel()
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAssignedDeveloperName()
    {
        return assignedDeveloperName;
    }

    public void setAssignedDeveloperName(String assignedDeveloperName)
    {
        this.assignedDeveloperName = assignedDeveloperName;
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
}
