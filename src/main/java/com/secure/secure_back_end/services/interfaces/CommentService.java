package com.secure.secure_back_end.services.interfaces;

import com.secure.secure_back_end.dto.comment.binding.CommentCreateForm;
import com.secure.secure_back_end.dto.comment.binding.CommentEditForm;
import com.secure.secure_back_end.dto.comment.view.CommentDetailsView;

import java.util.List;

public interface CommentService
{
    List<CommentDetailsView> insertComment(CommentCreateForm form, Long ticketId);

    List<CommentDetailsView> findByTicket(long ticketId);

    List<CommentDetailsView> findBySubmitter(Long userId);

    void editComment(CommentEditForm form, Long commentId);

    void deleteComment(Long commentId);
}
