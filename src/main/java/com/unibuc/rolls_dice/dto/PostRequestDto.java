package com.unibuc.rolls_dice.dto;

import jakarta.persistence.Column;
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
public class PostRequestDto {
    @NotNull(message = "Post text must not be null.")
    @Size(min = 1, message = "Post text must contain at least 1 character.")
    private String text;

    @NotNull(message = "Username must not be null.")
    private String username;
}
