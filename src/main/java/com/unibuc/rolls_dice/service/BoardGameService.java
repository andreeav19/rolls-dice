package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameResponseDto;

import java.util.List;

public interface BoardGameService {
    List<BoardGameResponseDto> getAllBoardGames();
}
