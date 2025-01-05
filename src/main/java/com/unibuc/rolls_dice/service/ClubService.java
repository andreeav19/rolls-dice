package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.ClubRequestDto;

public interface ClubService {
    Long addClub(String username, ClubRequestDto clubRequestDto);
}
