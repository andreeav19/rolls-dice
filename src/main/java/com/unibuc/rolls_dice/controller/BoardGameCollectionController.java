package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.BoardGameCollectionRequestDto;
import com.unibuc.rolls_dice.dto.RatingRequestDto;
import com.unibuc.rolls_dice.service.BoardGameCollectionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board-game-collection")
@RequiredArgsConstructor
public class BoardGameCollectionController {
    private final BoardGameCollectionService boardGameCollectionService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addBoardGameToCollectionWithUsername(@RequestBody @Valid BoardGameCollectionRequestDto requestDto) {
        boardGameCollectionService.addBoardGameToCollectionWithUsername(requestDto);
    }

    @PostMapping("/rating")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRatingToBoardGameCollection(@RequestBody @Valid RatingRequestDto requestDto) {
        boardGameCollectionService.addRatingToBoardGame(requestDto);
    }
}
