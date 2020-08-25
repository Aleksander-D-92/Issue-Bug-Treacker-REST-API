package com.secure.secure_back_end.dto.comment.binding;

public class CommentCreateForm
{
    private String description;
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
