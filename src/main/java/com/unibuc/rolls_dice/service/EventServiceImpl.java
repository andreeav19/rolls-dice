package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.EventRequestDto;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.Event;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.ClubRepository;
import com.unibuc.rolls_dice.repository.EventRepository;
import com.unibuc.rolls_dice.repository.RollsDiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final RollsDiceUserRepository rollsDiceUserRepository;
    private final ClubRepository clubRepository;
    private final DatabaseLookup databaseLookup;

    public Long addEvent(Long clubId, EventRequestDto eventRequestDto) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(eventRequestDto.getUsername());
        Club club = databaseLookup.retrieveClubById(clubId);
        databaseLookup.checkUserIsLeaderOfClub(user, club);

        Event event = new Event();
        event.setName(eventRequestDto.getName());
        event.setDescription(eventRequestDto.getDescription());
        event.setLocation(eventRequestDto.getLocation());
        event.setTime(eventRequestDto.getTime());
        event.setMaxAttendees(eventRequestDto.getMaxAttendees());
        event.setUserList(new ArrayList<>(List.of(user)));
        event.setClub(club);
        eventRepository.save(event);

        if (user.getAttendedEventList() == null) {
            user.setAttendedEventList(new ArrayList<>(List.of(event)));
        } else {
            user.getAttendedEventList().add(event);
        }
        rollsDiceUserRepository.save(user);

        if (club.getEventList() == null) {
            club.setEventList(new ArrayList<>(List.of(event)));
        } else {
            club.getEventList().add(event);
        }
        clubRepository.save(club);

        return event.getEventId();
    }

    public void addUserToEventAttendees(String username, Long eventId) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);
        Event event = databaseLookup.retrieveEventById(eventId);
        databaseLookup.checkUserIsMemberOfClub(user, event.getClub());

        int attendeesCount = event.getUserList().size();
        if (event.getMaxAttendees() == attendeesCount) {
            throw new IllegalStateException("Maximum limit for event attendees with the id " + eventId + " exceeded.");
        }

        boolean userExists = event.getUserList().stream()
                .anyMatch(existingUser -> existingUser.getUserId().equals(user.getUserId()));
        if (userExists) {
            return;
        }

        event.getUserList().add(user);
        eventRepository.save(event);

        if (user.getAttendedEventList() == null) {
            user.setAttendedEventList(new ArrayList<>(List.of(event)));
        } else {
            user.getAttendedEventList().add(event);
        }
        rollsDiceUserRepository.save(user);
    }
}
