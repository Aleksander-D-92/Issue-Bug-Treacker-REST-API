package com.secure.secure_back_end.dto.comment.binding;

import javax.validation.constraints.Size;

public class CommentEditForm
{
    @Size(min = 10, max = 255, message = "description must be between 10 and 255 symbols long")
    private String description;

    public CommentEditForm()
    {
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
