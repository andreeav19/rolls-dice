package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.EventRequestDto;

public interface EventService {
    Long addEvent(Long clubId, EventRequestDto eventRequestDto);
    void addUserToEventAttendees(String username, Long eventId);
}
