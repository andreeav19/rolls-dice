package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.ClubRequestDto;

public interface ClubService {
    Long addClub(ClubRequestDto clubRequestDto);
    void editClub(Long clubId, ClubRequestDto clubRequestDto);
    void addUserToClubMembers(String username, Long clubId);
}
