package com.secure.secure_back_end.dto.comment.view;

import java.util.Date;

public class CommentViewModel
{
    private Long commentId;
    private Long submitterId;
    private Long ticketId;
    private String description;
    private String submitterName;
    private String ticketTitle;
    private Date creationDate;

    public CommentViewModel()
    {
    }

    public Long getCommentId()
    {
        return commentId;
    }

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public Long getSubmitterId()
    {
        return submitterId;
    }

    public void setSubmitterId(Long submitterId)
    {
        this.submitterId = submitterId;
    }

    public Long getTicketId()
    {
        return ticketId;
    }

    public void setTicketId(Long ticketId)
    {
        this.ticketId = ticketId;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getSubmitterName()
    {
        return submitterName;
    }

    public void setSubmitterName(String submitterName)
    {
        this.submitterName = submitterName;
    }

    public String getTicketTitle()
    {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle)
    {
        this.ticketTitle = ticketTitle;
    }

    public Date getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(Date creationDate)
    {
        this.creationDate = creationDate;
    }
}
