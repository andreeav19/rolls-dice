package com.unibuc.rolls_dice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardGameResponseDto {
    private String name;
    private Float ratingScore;
    private String description;
    private String rulesLink;
    private List<CategoryResponseDto> categoryList;
}
