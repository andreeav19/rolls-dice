package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RollsDiceUserController {
    private final ClubService clubService;

    @PostMapping("/{username}/club/{clubId}/board-game/{boardGameId}")
    @ResponseStatus(HttpStatus.OK)
    public void addBoardGameToClub(
            @PathVariable String username, @PathVariable Long clubId, @PathVariable Long boardGameId) {
        clubService.addBoardGameToClub(username, clubId, boardGameId);
    }

    @PutMapping("/{username}/club/{clubId}/board-game/{boardGameId}")
    @ResponseStatus(HttpStatus.OK)
    public void editClubBoardGame(
            @PathVariable String username, @PathVariable Long clubId, @PathVariable Long boardGameId) {
        clubService.editClubBoardGame(username, clubId, boardGameId);
    }
}
