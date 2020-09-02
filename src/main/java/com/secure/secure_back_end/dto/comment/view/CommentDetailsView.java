package com.secure.secure_back_end.dto.comment.view;

import com.secure.secure_back_end.dto.ticket.view.TicketVIew;
import com.secure.secure_back_end.dto.user.view.UserView;

import java.util.Date;

public class CommentDetailsView
{
    private Long commentId;
    private String description;
    private Date creationDate;
    public TicketVIew ticket;
    public UserView submitter;

    public CommentDetailsView()
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

    public TicketVIew getTicket()
    {
        return ticket;
    }

    public void setTicket(TicketVIew ticket)
    {
        this.ticket = ticket;
    }

    public UserView getSubmitter()
    {
        return submitter;
    }

    public void setSubmitter(UserView submitter)
    {
        this.submitter = submitter;
    }
}
