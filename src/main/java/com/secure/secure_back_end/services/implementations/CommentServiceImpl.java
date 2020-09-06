package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Comment;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.comment.binding.CommentCreateForm;
import com.secure.secure_back_end.dto.comment.binding.CommentEditForm;
import com.secure.secure_back_end.dto.comment.view.CommentDetailsView;
import com.secure.secure_back_end.dto.ticket.view.TicketVIew;
import com.secure.secure_back_end.dto.user.view.UserView;
import com.secure.secure_back_end.repositories.CommentRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import com.secure.secure_back_end.services.interfaces.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService
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

    @Override
    public List<CommentDetailsView> insertComment(CommentCreateForm form, Long ticketId)
    {
        User user = this.userRepository.getOne(form.getUserId());
        Ticket ticket = this.ticketRepository.getOne(ticketId);
        Comment comment = this.modelMapper.map(form, Comment.class);
        comment.setTicket(ticket);
        comment.setSubmitter(user);
        comment.setCreationDate(new Date());
        Comment saved = this.commentRepository.save(comment);
        return map(Collections.singletonList(saved));
    }

    @Override
    public List<CommentDetailsView> findByTicket(long ticketId)
    {
        Ticket ticket = this.ticketRepository.getOne(ticketId);
        return map(this.commentRepository.findAllByTicket(ticket));
    }

    @Override
    public List<CommentDetailsView> findBySubmitter(Long userId)
    {
        User submitter = this.userRepository.getOne(userId);
        return map(this.commentRepository.findAllBySubmitter(submitter));

    }

    private List<CommentDetailsView> map(List<Comment> comments)
    {
        return comments.stream()
                .map(comment ->
                {
                    CommentDetailsView map = this.modelMapper.map(comment, CommentDetailsView.class);
                    UserView user = this.modelMapper.map(comment.getSubmitter(), UserView.class);
                    TicketVIew ticket = this.modelMapper.map(comment.getTicket(), TicketVIew.class);
                    map.setSubmitter(user);
                    map.setTicket(ticket);
                    return map;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void editComment(CommentEditForm form, Long commentId)
    {
        this.commentRepository.editComment(form.getDescription(), commentId);
    }

    @Override
    public void deleteComment(Long commentId)
    {
        this.commentRepository.deleteById(commentId);
    }
}
