package com.unibuc.rolls_dice.dto;

import com.unibuc.rolls_dice.validator.DivisibleByHalf;
import jakarta.validation.constraints.Max;
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
public class RatingRequestDto {
    @NotNull(message = "Username must not be null.")
    private String username;

    @NotNull(message = "Board game id must not be null.")
    @Min(value = 1, message = "Board game id must be greater than 0.")
    private Long boardGameId;

    @Min(value = 0, message = "Rating score value should be greater than or equal to 0.")
    @Max(value = 5, message = "Rating score value should less than or equal to 0.")
    @DivisibleByHalf(message = "Rating score value should be divisible by 0.5.")
    private Float ratingScore;

    @Size(min = 1, message = "Rating description should contain at least 1 character.")
    private String ratingDescription;
}
