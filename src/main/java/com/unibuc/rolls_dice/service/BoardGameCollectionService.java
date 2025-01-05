package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameCollectionRequestDto;
import com.unibuc.rolls_dice.dto.RatingRequestDto;

public interface BoardGameCollectionService {
    void addBoardGameToCollectionWithUsername(BoardGameCollectionRequestDto boardGameCollectionRequestDto);
    void addRatingToBoardGame(RatingRequestDto ratingRequestDto);
}
