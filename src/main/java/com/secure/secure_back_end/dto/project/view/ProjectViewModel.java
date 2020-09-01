package com.secure.secure_back_end.dto.project.view;

import com.secure.secure_back_end.dto.user.view.UserViewModel;

import java.util.Date;

public class ProjectViewModel
{
    private Long projectId;
    private String title;
    private String description;
    private Date creationDate;
    private UserViewModel projectManager;
    public ProjectViewModel()
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

    public UserViewModel getProjectManager()
    {
        return projectManager;
    }

    public void setProjectManager(UserViewModel projectManager)
    {
        this.projectManager = projectManager;
    }
}
