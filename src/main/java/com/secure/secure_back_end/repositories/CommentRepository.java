package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    @Query("select c from comments as c join fetch c.ticket join fetch c.user where c.ticket.id=:ticket_id")
    List<Comment> getAllByTicketId(@Param("ticket_id") Long ticketId);

    @Query("select c from comments as c join fetch c.ticket join fetch c.user where c.user.userId=:user_id")
    List<Comment> getAllByUserId(@Param("user_id") Long userId);

    @Modifying
    @Transactional
    @Query("update comments c set c.description=:description where c.id=:comment_id")
    void editComment(@Param("description") String description, @Param("comment_id") Long commentId);
}
