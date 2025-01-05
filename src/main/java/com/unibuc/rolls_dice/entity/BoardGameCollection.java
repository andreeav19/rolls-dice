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

    private Float ratingScore;
    private String ratingDescription;

    @Column(nullable = false)
    private Boolean wantToBuy;

    @Column(nullable = false)
    private Boolean own;

    @Column(nullable = false)
    private Boolean recommend;

    @PrePersist
    private void ensureCollectionTypeDefaults() {
        if (wantToBuy == null) {
            wantToBuy = false;
        }

        if (own == null) {
            own = false;
        }

        if (recommend == null) {
            recommend = false;
        }
    }
}
