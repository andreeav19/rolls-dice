package com.unibuc.rolls_dice.entity.key;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BoardGameCollectionId implements Serializable {
    private UUID user;
    private Long boardGame;
}
