package com.unibuc.rolls_dice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clubId;

    @Column(nullable = false)
    private String name;

    private String description;
    private Integer maxMembers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private RollsDiceUser user;

    @ManyToOne
    @JoinColumn(name = "board_game_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private BoardGame boardGame;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Post> postList;

    @OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
    private List<Event> eventList;
}
