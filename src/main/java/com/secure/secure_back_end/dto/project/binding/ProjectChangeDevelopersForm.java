package com.secure.secure_back_end.dto.project.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProjectChangeDevelopersForm
{
    @NotNull
    @Size(min=1, message = "developerIds.size() must be atleast 1")
    private List<Long> developerIds;


    public ProjectChangeDevelopersForm()
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


}
