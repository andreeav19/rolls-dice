package com.unibuc.rolls_dice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    private Long eventId;
    private String name;
    private String description;
    private String location;
    private LocalDateTime time;
    private Integer maxAttendees;
    private String winner;
    private String organizer;
    private List<String> attendees;
}
