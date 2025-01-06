package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.RatingRequestDto;
import com.unibuc.rolls_dice.service.BoardGameCollectionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = BoardGameCollectionController.class)
@ExtendWith(MockitoExtension.class)
class BoardGameCollectionControllerTest {
    @Autowired
    private BoardGameCollectionController collectionController;

    @MockitoBean
    private BoardGameCollectionService collectionService;

    @Test
    void addRatingToBoardGameCollection() {
        RatingRequestDto requestDto = new RatingRequestDto(
                "username",
                1L,
                5f,
                null
        );
        doNothing().when(collectionService).addRatingToBoardGame(requestDto);
        collectionController.addRatingToBoardGameCollection(requestDto);
        verify(collectionService, times(1)).addRatingToBoardGame(requestDto);
    }
}