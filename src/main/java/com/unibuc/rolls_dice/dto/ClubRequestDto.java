package com.unibuc.rolls_dice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubRequestDto {
    @NotNull(message = "Username must not be null.")
    private String username;

    @NotNull
    @Size(min = 1, message = "Club name must contain at least 1 character.")
    private String name;

    private String description;

    @Min(value = 1, message = "Maximum number of club members should be greater than 0.")
    private Integer maxMembers;
}
