package com.unibuc.rolls_dice.dto;

import com.unibuc.rolls_dice.validator.EventTime;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto {
    @NotNull(message = "Username must not be null.")
    private String username;

    @NotNull(message = "Event name must not be null.")
    @Size(min = 1, message = "Event name must contain at least 1 character.")
    private String name;

    @Size(min = 1, message = "Event description must contain at least 1 character.")
    private String description;

    @NotNull(message = "Event location must not be null.")
    @Size(min = 1, message = "Event location must contain at least 1 character.")
    private String location;

    @NotNull(message = "Event time text must not be null.")
    @EventTime
    private LocalDateTime time;

    @Min(value = 1, message = "Maximum number of event attendees should be greater than 0.")
    private Integer maxAttendees;
}
