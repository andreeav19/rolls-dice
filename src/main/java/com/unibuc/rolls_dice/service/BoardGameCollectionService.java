package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameCollectionRequestDto;

public interface BoardGameCollectionService {
    public void addBoardGameToCollectionWithUsername(BoardGameCollectionRequestDto boardGameCollectionRequestDto);
}
