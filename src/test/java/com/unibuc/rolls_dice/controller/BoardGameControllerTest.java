package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.BoardGameResponseDto;
import com.unibuc.rolls_dice.service.BoardGameService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = BoardGameController.class)
@ExtendWith(MockitoExtension.class)
class BoardGameControllerTest {
    @Autowired
    private BoardGameController boardGameController;

    @MockitoBean
    private BoardGameService boardGameService;

    @Test
    void testGetAllBoardGames() {
        BoardGameResponseDto b1 = new BoardGameResponseDto(
                "Game 1",
                null,
                "Description 1",
                "Rules link 1",
                null
        );
        BoardGameResponseDto b2 = new BoardGameResponseDto(
                "Game 2",
                null,
                "Description 2",
                "Rules link 2",
                null
        );
        List<BoardGameResponseDto> bList = Arrays.asList(b1, b2);

        when(boardGameService.getAllBoardGames()).thenReturn(bList);

        ResponseEntity<List<BoardGameResponseDto>> result = boardGameController.getAllBoardGames();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(bList, result.getBody());
    }

}