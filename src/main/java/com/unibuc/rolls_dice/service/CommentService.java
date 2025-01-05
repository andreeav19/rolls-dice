package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.CommentRequestDto;

public interface CommentService {
    Long addCommentToPost(Long postId, CommentRequestDto commentRequestDto);
}
