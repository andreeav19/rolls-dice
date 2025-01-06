package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.CommentRequestDto;
import com.unibuc.rolls_dice.dto.CommentResponseDto;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.Comment;
import com.unibuc.rolls_dice.entity.Post;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final DatabaseLookup databaseLookup;

    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        databaseLookup.retrievePostById(postId);

        return commentRepository.findCommentsByPost_PostId(postId).stream()
                .map(comment -> {
                    return new CommentResponseDto(
                            comment.getCommentId(),
                            comment.getText(),
                            comment.getUser().getUsername(),
                            comment.getTime()
                    );})
                .toList();
    }

    public Long addCommentToPost(Long postId, CommentRequestDto commentRequestDto) {
        Post post = databaseLookup.retrievePostById(postId);
        Club club = databaseLookup.retrieveClubById(post.getClub().getClubId());
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(commentRequestDto.getUsername());

        databaseLookup.checkUserIsMemberOfClub(user, club);

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setText(commentRequestDto.getText());
        comment.setTime(LocalDateTime.now());

        if (post.getCommentList() == null) {
            post.setCommentList(new ArrayList<>(List.of(comment)));
        } else {
            post.getCommentList().add(comment);
        }

        if (user.getCommentList() == null) {
            user.setCommentList(new ArrayList<>(List.of(comment)));
        } else {
            user.getCommentList().add(comment);
        }

        commentRepository.save(comment);
        return comment.getCommentId();
    }
}
