package com.secure.secure_back_end.dto.project.view;

import java.util.Date;

public class ProjectViewModel
{
    private long projectId;
    private String title;
    private String description;
    private Date creationDate;
    private long projectManagerId;
    private String projectManagerName;

    public ProjectViewModel()
    {
    }

    public long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(long projectId)
    {
        this.projectId = projectId;
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

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }

    public long getProjectManagerId()
    {
        return projectManagerId;
    }

    public void setProjectManagerId(long projectManagerId)
    {
        this.projectManagerId = projectManagerId;
    }

    public String getProjectManagerName()
    {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName)
    {
        this.projectManagerName = projectManagerName;
    }
}
