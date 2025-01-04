package com.unibuc.rolls_dice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardGameId;

    @Column(nullable = false)
    private String name;

    private Float ratingScore;

    @Column(nullable = false)
    private String description;

    private String rulesLink;

    @OneToMany(mappedBy = "boardGame", cascade = CascadeType.ALL)
    private List<BoardGameCollection> boardGameCollectionList;

    @OneToMany(mappedBy = "boardGame", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Club> clubList;

    @ManyToMany
    @JoinTable(
            name = "board_game_category",
            joinColumns = @JoinColumn(name = "board_game_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categoryList;
}
