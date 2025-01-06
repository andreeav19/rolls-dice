package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.EventRequestDto;
import com.unibuc.rolls_dice.dto.EventResponseDto;
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

    public List<EventResponseDto> getEventsByClubId(Long clubId) {
        databaseLookup.retrieveClubById(clubId);

        return eventRepository.findEventsByClub_ClubId(clubId).stream()
                .map(event -> {
                    return new EventResponseDto(
                            event.getEventId(),
                            event.getName(),
                            event.getDescription(),
                            event.getLocation(),
                            event.getTime(),
                            event.getMaxAttendees(),
                            event.getWinner() != null ? event.getWinner().getUsername() : null,
                            event.getClub().getLeader().getUsername(),
                            event.getUserList().stream()
                                    .map(RollsDiceUser::getUsername).toList()
                    );})
                .toList();
    }

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

    public void addWinnerToEvent(String username, String winnerUsername, Long eventId) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);
        RollsDiceUser winner = databaseLookup.retrieveUserByUsername(winnerUsername);
        Event event = databaseLookup.retrieveEventById(eventId);

        databaseLookup.checkEventWinnerIsNotSet(event);
        databaseLookup.checkUserIsLeaderOfClub(user, event.getClub());
        databaseLookup.checkUserIsAttendeeOfEvent(winner, event);

        event.setWinner(winner);
        eventRepository.save(event);

        if (winner.getWonEventList() == null) {
            winner.setWonEventList(new ArrayList<>(List.of(event)));
        } else {
            winner.getWonEventList().add(event);
        }
        rollsDiceUserRepository.save(winner);
    }

    public void deleteEventById(Long eventId) {
        Event event = databaseLookup.retrieveEventById(eventId);
        event.getUserList().forEach(user -> user.getAttendedEventList().remove(event));
        event.getUserList().clear();

        if (event.getWinner() != null)
            event.getWinner().getWonEventList().remove(event);

        eventRepository.delete(event);
    }
}
