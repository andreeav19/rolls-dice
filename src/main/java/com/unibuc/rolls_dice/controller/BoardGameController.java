package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.BoardGameResponseDto;
import com.unibuc.rolls_dice.service.BoardGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/board-games")
@RequiredArgsConstructor
public class BoardGameController {
    private final BoardGameService boardGameService;

    @GetMapping
    public ResponseEntity<List<BoardGameResponseDto>> getAllBoardGames() {
        List<BoardGameResponseDto> boardGames = boardGameService.getAllBoardGames();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardGames);
    }
}
