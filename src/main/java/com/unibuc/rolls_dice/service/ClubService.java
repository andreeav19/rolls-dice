package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.ClubRequestDto;
import com.unibuc.rolls_dice.dto.ClubResponseDto;

import java.util.List;

public interface ClubService {
    Long addClub(ClubRequestDto clubRequestDto);
    void editClub(Long clubId, ClubRequestDto clubRequestDto);
    void addUserToClubMembers(String username, Long clubId);
    List<ClubResponseDto> getClubsByName(String clubName);
    List<ClubResponseDto> getClubsByCategories(List<Integer> categories);
}
