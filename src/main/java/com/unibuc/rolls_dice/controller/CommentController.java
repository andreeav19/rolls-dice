package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.CommentRequestDto;
import com.unibuc.rolls_dice.dto.PostRequestDto;
import com.unibuc.rolls_dice.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add-to-post/{postId}")
    public ResponseEntity<Long> addPostToClub(@PathVariable Long postId, @RequestBody @Valid CommentRequestDto requestDto) {
        Long response = commentService.addCommentToPost(postId, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
