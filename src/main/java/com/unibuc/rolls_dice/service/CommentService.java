package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.CommentRequestDto;
import com.unibuc.rolls_dice.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {
    Long addCommentToPost(Long postId, CommentRequestDto commentRequestDto);
    List<CommentResponseDto> getCommentsByPostId(Long postId);
}
