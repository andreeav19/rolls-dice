package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.ClubRequestDto;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.ClubRepository;
import com.unibuc.rolls_dice.repository.RollsDiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final RollsDiceUserRepository rollsDiceUserRepository;
    private final DatabaseLookup databaseLookup;

    public Long addClub(String username, ClubRequestDto clubRequestDto) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);

        Club club = new Club();
        club.setName(clubRequestDto.getName());
        club.setDescription(clubRequestDto.getDescription());
        club.setMaxMembers(clubRequestDto.getMaxMembers());
        club.setLeader(user);
        club.setUserList(new ArrayList<>(List.of(user)));
        clubRepository.save(club);

        user.setJoinedClubList(new ArrayList<>(List.of(club)));
        user.setLedClubList(new ArrayList<>(List.of(club)));
        rollsDiceUserRepository.save(user);

        return club.getClubId();
    }
}
