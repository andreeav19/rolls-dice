package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.PostRequestDto;

public interface PostService {
    Long addPostToClub(Long clubId, PostRequestDto postRequestDto);
}
