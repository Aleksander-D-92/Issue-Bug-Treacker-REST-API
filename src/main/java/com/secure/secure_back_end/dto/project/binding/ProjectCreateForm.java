package com.secure.secure_back_end.dto.project.binding;

import javax.validation.constraints.Size;

public class ProjectCreateForm
{
    @Size(min = 5, max = 50, message = "title must be between 10 and 50 symbols long")
    private String title;
    @Size(min = 10, max = 255, message = "description must be between 10 and 255 symbols long")
    private String description;

    public ProjectCreateForm()
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
}
