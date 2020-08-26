package com.secure.secure_back_end.dto.comment.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class CommentCreateForm
{
    @Size(min = 10, max = 200)
    private String description;
    @Min(1)
    private Long userId;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
}
