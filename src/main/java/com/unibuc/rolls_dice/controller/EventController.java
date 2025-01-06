package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.EventRequestDto;
import com.unibuc.rolls_dice.dto.EventResponseDto;
import com.unibuc.rolls_dice.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/club/{clubId}")
    public ResponseEntity<List<EventResponseDto>> getEventsByClubId(@PathVariable Long clubId) {
        List<EventResponseDto> response = eventService.getEventsByClubId(clubId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PostMapping("/add-to-club/{clubId}")
    public ResponseEntity<Long> addEvent(@PathVariable Long clubId, @RequestBody @Valid EventRequestDto requestDto) {
        Long response = eventService.addEvent(clubId, requestDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{username}/attend/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public void addUserToEventAttendees(@PathVariable String username, @PathVariable Long eventId) {
        eventService.addUserToEventAttendees(username, eventId);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEventById(@PathVariable Long eventId) {
        eventService.deleteEventById(eventId);
    }
}
