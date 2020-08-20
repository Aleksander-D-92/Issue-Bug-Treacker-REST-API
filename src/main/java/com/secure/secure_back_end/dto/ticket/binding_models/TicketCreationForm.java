package com.secure.secure_back_end.dto.ticket.binding_models;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;

public class TicketCreationForm
{
    private Long projectId;
    private Long submitterId;
    private String title;
    private String description;
    private Category category;
    private Priority priority;

    public TicketCreationForm()
    {
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
}
