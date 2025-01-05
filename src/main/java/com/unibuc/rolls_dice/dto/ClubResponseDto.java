package com.unibuc.rolls_dice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubResponseDto {
    private Long clubId;
    private String name;
    private String description;
    private String leaderUsername;
    private Integer maxMembers;
    private BoardGameResponseDto boardGame;
    private List<String> members;
}
