package com.unibuc.rolls_dice.dto;

import com.unibuc.rolls_dice.type.CollectionType;
import com.unibuc.rolls_dice.validator.ValidCollectionType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameCollectionRequestDto {
    @NotNull(message = "Username must not be null.")
    String username;

    @NotNull(message = "Board game id must not be null.")
    @Min(value = 1, message = "Board game id must be greater than 0.")
    Long boardGameId;

    @NotNull(message = "Collection type must not be null.")
    CollectionType collectionType;
}
