package com.secure.secure_back_end.dto.project.binding;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ProjectQAForm
{
    @NotNull
    @Size(min=1)
    private List<Long> qaIds;


    public ProjectQAForm()
    {
    }

    public List<Long> getQaIds()
    {
        return qaIds;
    }

    public void setQaIds(List<Long> qaIds)
    {
        this.qaIds = qaIds;
    }


}
