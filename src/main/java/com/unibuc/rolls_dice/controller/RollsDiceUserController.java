package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.ClubRequestDto;
import com.unibuc.rolls_dice.service.ClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RollsDiceUserController {
    private final ClubService clubService;

    @PostMapping("{username}/add-club")
    public ResponseEntity<Long> addClub(@PathVariable String username, @RequestBody @Valid ClubRequestDto requestDto) {
        Long response = clubService.addClub(username, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("{username}/edit-club/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public void editClub(@PathVariable String username, @PathVariable Long clubId,
                         @RequestBody @Valid ClubRequestDto requestDto) {
        clubService.editClub(username, clubId, requestDto);
    }

    @PostMapping("{username}/join-club/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToClubMembers(@PathVariable String username, @PathVariable Long clubId) {
        clubService.addUserToClubMembers(username, clubId);
    }
}
