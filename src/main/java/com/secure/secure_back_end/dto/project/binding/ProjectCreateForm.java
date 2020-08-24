package com.secure.secure_back_end.dto.project.binding;

public class ProjectCreateForm
{
    private long ownerId;
    private String title;
    private String description;

    public ProjectCreateForm()
    {
    }

    public long getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(long ownerId)
    {
        this.ownerId = ownerId;
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
