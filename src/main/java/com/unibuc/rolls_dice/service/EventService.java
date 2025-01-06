package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.EventRequestDto;
import com.unibuc.rolls_dice.dto.EventResponseDto;

import java.util.List;

public interface EventService {
    Long addEvent(Long clubId, EventRequestDto eventRequestDto);
    void addUserToEventAttendees(String username, Long eventId);
    void addWinnerToEvent(String username, String winnerUsername, Long eventId);
    List<EventResponseDto> getEventsByClubId(Long clubId);
    void deleteEventById(Long eventId);
}
