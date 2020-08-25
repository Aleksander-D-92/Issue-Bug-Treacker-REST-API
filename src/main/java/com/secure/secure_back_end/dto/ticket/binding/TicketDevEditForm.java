package com.secure.secure_back_end.dto.ticket.binding;

import com.secure.secure_back_end.domain.enums.Category;
import com.secure.secure_back_end.domain.enums.Priority;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TicketDevEditForm
{
    @Size(min = 5, max = 30)
    private String title;
    @Size(min = 5, max = 220)
    private String description;
    @NotNull
    private Category category;
    @NotNull
    private Priority priority;

    public TicketDevEditForm()
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
