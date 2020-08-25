package com.secure.secure_back_end.dto.project.binding;

import javax.validation.constraints.Size;

public class ProjectEditForm
{
    @Size(min = 5, max = 30)
    private String title;
    @Size(min = 5, max = 220)
    private String description;


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
}
