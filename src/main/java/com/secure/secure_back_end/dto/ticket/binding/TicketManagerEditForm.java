package com.secure.secure_back_end.dto.ticket.binding;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;
import com.secure.secure_back_end.domain.enums.Status;

public class TicketManagerEditForm
{
    private String title;
    private String description;
    private Category category;
    private Priority priority;
    private Status status;
    private Long assignedDeveloperId;


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

    public Long getAssignedDeveloperId()
    {
        return assignedDeveloperId;
    }

    public void setAssignedDeveloperId(Long assignedDeveloperId)
    {
        this.assignedDeveloperId = assignedDeveloperId;
    }
}
