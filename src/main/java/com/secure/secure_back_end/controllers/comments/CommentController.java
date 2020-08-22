package com.secure.secure_back_end.controllers.comments;

import com.secure.secure_back_end.services.implementations.CommentServiceImpl;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController
{
    private final CommentServiceImpl commentService;

    public CommentController(CommentServiceImpl commentService)
    {
        this.commentService = commentService;
    }
}
