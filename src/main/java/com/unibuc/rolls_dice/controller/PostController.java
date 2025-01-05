package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.PostRequestDto;
import com.unibuc.rolls_dice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/add-to-club/{clubId}")
    public ResponseEntity<Long> addPostToClub(@PathVariable Long clubId, @RequestBody @Valid PostRequestDto requestDto) {
        Long response = postService.addPostToClub(clubId, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
