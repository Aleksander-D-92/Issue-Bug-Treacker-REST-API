package com.secure.secure_back_end.controllers.comments;

import com.secure.secure_back_end.dto.comment.binding.CommentCreateForm;
import com.secure.secure_back_end.dto.comment.binding.CommentEditForm;
import com.secure.secure_back_end.dto.comment.view.CommentViewModel;
import com.secure.secure_back_end.services.interfaces.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class CommentController
{
    private final CommentService commentService;

    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    @GetMapping("/comments/ticket/{ticketId}")
    @ApiOperation(value = "get all comments by a given ticketId")
    public List<CommentViewModel> getCommentsForTicket(@PathVariable("ticketId") @Min(1) Long ticketId)
    {
        return this.commentService.getTicketComments(ticketId);
    }

    @GetMapping("/comments/user/{userId}")
    @ApiOperation(value = "get all comments submitted by the user")
    public List<CommentViewModel> getCommentsForUser(@PathVariable("userId") @Min(1) Long userId)
    {
        return this.commentService.getUserComments(userId);
    }

    @PostMapping("/comments/{ticketId}")
    @ApiOperation(value = "submits a new comment by a given ticketId")
    public void submitComment(@Valid @RequestBody CommentCreateForm form,
                              @PathVariable("ticketId") @Min(1) Long ticketId)
    {
        this.commentService.insertComment(form, ticketId);
    }

    @PutMapping("/comments/{commentId}")
    @ApiOperation(value = "edits comment by commentId")
    public void editComment(@Valid @RequestBody CommentEditForm form,
                            @PathVariable("commentId") @Min(1) Long commentId)
    {
        this.commentService.editComment(form, commentId);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable("commentId") @Min(1) Long commentId)
    {
        this.commentService.deleteComment(commentId);
    }
}
