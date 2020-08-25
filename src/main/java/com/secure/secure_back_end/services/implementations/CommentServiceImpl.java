package com.secure.secure_back_end.services.implementations;

import com.secure.secure_back_end.domain.Comment;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import com.secure.secure_back_end.dto.comment.binding.CommentCreateForm;
import com.secure.secure_back_end.dto.comment.binding.CommentEditForm;
import com.secure.secure_back_end.dto.comment.view.CommentViewModel;
import com.secure.secure_back_end.repositories.CommentRepository;
import com.secure.secure_back_end.repositories.TicketRepository;
import com.secure.secure_back_end.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public void insertComment(CommentCreateForm form, Long ticketId)
    {
        User user = this.userRepository.getOne(form.getUserId());
        Ticket ticket = this.ticketRepository.getOne(ticketId);
        Comment comment = this.modelMapper.map(form, Comment.class);
        comment.setTicket(ticket);
        comment.setUser(user);
        comment.setCreationDate(new Date());
        this.commentRepository.save(comment);
    }

    public List<CommentViewModel> getTicketComments(long ticketId)
    {
        return this.commentRepository.getAllByTicketId(ticketId).stream().map(comment -> this.modelMapper.map(comment, CommentViewModel.class)).collect(Collectors.toList());
    }

    public List<CommentViewModel> getUserComments(Long userId)
    {
        return this.commentRepository.getAllByUserId(userId).stream().map(comment -> this.modelMapper.map(comment, CommentViewModel.class)).collect(Collectors.toList());

    }

    public void editComment(CommentEditForm form, Long commentId)
    {
        this.commentRepository.editComment(form.getDescription(), commentId);
    }

    public void deleteComment(Long commentId)
    {
        this.commentRepository.deleteById(commentId);
    }
}
