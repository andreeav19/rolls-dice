package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByPost_PostId(Long postId);
}
