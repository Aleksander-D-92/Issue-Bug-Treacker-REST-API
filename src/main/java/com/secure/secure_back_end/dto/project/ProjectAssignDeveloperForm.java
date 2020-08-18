package com.secure.secure_back_end.dto.project;

import java.util.List;

public class ProjectAssignDeveloperForm
{
    private List<Long> developerIds;
    private long projectId;

    public ProjectAssignDeveloperForm()
    {
    }

    public List<Long> getDeveloperIds()
    {
        return developerIds;
    }

    public void setDeveloperIds(List<Long> developerIds)
    {
        this.developerIds = developerIds;
    }

    public long getProjectId()
    {
        return projectId;
    }

    public void setProjectId(long projectId)
    {
        this.projectId = projectId;
    }
}
