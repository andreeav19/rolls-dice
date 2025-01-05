package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.ClubRequestDto;
import com.unibuc.rolls_dice.service.ClubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @PostMapping()
    public ResponseEntity<Long> addClub(@RequestBody @Valid ClubRequestDto requestDto) {
        Long response = clubService.addClub(requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping("/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public void editClub(@PathVariable Long clubId,
                         @RequestBody @Valid ClubRequestDto requestDto) {
        clubService.editClub(clubId, requestDto);
    }

    @PostMapping("{username}/join/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToClubMembers(@PathVariable String username, @PathVariable Long clubId) {
        clubService.addUserToClubMembers(username, clubId);
    }
}
