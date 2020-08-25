package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>
{
    @Query("select c from comments as c join fetch c.ticket join fetch c.user where c.ticket.id=:ticket_id")
    List<Comment> getAllByTicketId(@Param("ticket_id") Long ticketId);

    @Query("select c from comments as c join fetch c.ticket join fetch c.user where c.user.id=:user_id")
    List<Comment> getAllByUserId(@Param("user_id") Long userId);
}
