package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Comment;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.comment.binding.CommentCreateForm;
import com.secure.secure_back_end.repositories.CommentRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl
{
    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, TicketRepository ticketRepository, UserRepository userRepository, ModelMapper modelMapper)
    {
        this.commentRepository = commentRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void insertComment(CommentCreateForm form)
    {
        User user = this.userRepository.getOne(form.getUserId());
        Ticket ticket = this.ticketRepository.getOne(form.getTicketId());
        Comment comment = this.modelMapper.map(form, Comment.class);
        comment.setTicket(ticket);
        comment.setUser(user);
        comment.setCreationDate(new Date());
        this.commentRepository.save(comment);
    }

    public List<Comment>  getTicketComments(long ticketId)
    {
        Ticket one = this.ticketRepository.getOne(ticketId);
        List<Comment> byTicket = this.commentRepository.findByTicket(one);
        return byTicket;
    }


}
