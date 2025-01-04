package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameResponseDto;
import com.unibuc.rolls_dice.dto.CategoryResponseDto;
import com.unibuc.rolls_dice.entity.Category;
import com.unibuc.rolls_dice.repository.BoardGameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardGameServiceImpl implements BoardGameService {
    private final BoardGameRepository boardGameRepository;

    public List<BoardGameResponseDto> getAllBoardGames() {
        return boardGameRepository.findAll().stream()
                .map(boardGame -> new BoardGameResponseDto(
                        boardGame.getName(),
                        boardGame.getRatingScore(),
                        boardGame.getDescription(),
                        boardGame.getRulesLink(),
                        boardGame.getCategoryList().stream()
                                .map(Category::getName)
                                .collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }
}
