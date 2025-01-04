package com.unibuc.rolls_dice.entity;

import com.unibuc.rolls_dice.entity.key.BoardGameCollectionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(BoardGameCollectionId.class)
public class BoardGameCollection {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private RollsDiceUser user;

    @Id
    @ManyToOne
    @JoinColumn(name = "board_game")
    private BoardGame boardGame;

    private Integer ratingScore;
    private String ratingDescription;
    private Boolean wantToBuy;
    private Boolean own;
    private Boolean recommend;
}
