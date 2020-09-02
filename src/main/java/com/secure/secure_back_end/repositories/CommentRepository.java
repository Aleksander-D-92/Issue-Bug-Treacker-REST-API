package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.Comment;
import com.secure.secure_back_end.domain.Ticket;
import com.secure.secure_back_end.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
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
    @EntityGraph(value = "ticketSubmitter")
    List<Comment> findAllByTicket(Ticket ticket);

    @EntityGraph(value = "ticketSubmitter")
    List<Comment> findAllBySubmitter(User user);

    @Modifying
    @Transactional
    @Query("update comments c set c.description=:description where c.commentId=:comment_id")
    void editComment(@Param("description") String description, @Param("comment_id") Long commentId);
}
