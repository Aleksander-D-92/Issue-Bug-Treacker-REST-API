package com.secure.secure_back_end.controllers.comments;

import com.secure.secure_back_end.dto.comment.binding.CommentCreateForm;
import com.secure.secure_back_end.services.implementations.CommentServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CommentController
{
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService)
    {
        this.commentService = commentService;
    }
    
    @PostMapping("/comments/submit")
    public void submitComment(@Valid @RequestBody CommentCreateForm form)
    {
        this.commentService.insertComment(form);
    }
}
