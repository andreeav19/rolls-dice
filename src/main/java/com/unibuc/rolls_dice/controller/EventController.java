package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.EventRequestDto;
import com.unibuc.rolls_dice.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping("/add-to-club/{clubId}")
    public ResponseEntity<Long> addEvent(@PathVariable Long clubId, @RequestBody @Valid EventRequestDto requestDto) {
        Long response = eventService.addEvent(clubId, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
