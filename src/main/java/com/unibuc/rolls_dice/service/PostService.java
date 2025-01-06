package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.PostRequestDto;
import com.unibuc.rolls_dice.dto.PostResponseDto;

import java.util.List;

public interface PostService {
    Long addPostToClub(Long clubId, PostRequestDto postRequestDto);
    List<PostResponseDto> getPostsByClubId(Long clubId);
}
