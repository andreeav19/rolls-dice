package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.PostRequestDto;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.Post;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.ClubRepository;
import com.unibuc.rolls_dice.repository.PostRepository;
import com.unibuc.rolls_dice.repository.RollsDiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private final RollsDiceUserRepository rollsDiceUserRepository;
    private final ClubRepository clubRepository;
    private final DatabaseLookup databaseLookup;

    public Long addPostToClub(Long clubId, PostRequestDto postRequestDto) {
        Club club = databaseLookup.retrieveClubById(clubId);
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(postRequestDto.getUsername());

        boolean userExists = club.getUserList().stream()
                .anyMatch(existingUser -> existingUser.getUserId().equals(user.getUserId()));
        if (!userExists) {
            throw new IllegalStateException("User " + user.getUsername()
                    + " is not a member of the club with the id " + clubId);
        }

        Post post = new Post();
        post.setClub(club);
        post.setUser(user);
        post.setText(postRequestDto.getText());
        post.setTime(LocalDateTime.now());
        postRepository.save(post);

        if (user.getPostList() == null) {
            user.setPostList(new ArrayList<>(List.of(post)));
        } else {
            user.getPostList().add(post);
        }
        rollsDiceUserRepository.save(user);

        if (club.getPostList() == null) {
            club.setPostList(new ArrayList<>(List.of(post)));
        } else {
            club.getPostList().add(post);
        }
        clubRepository.save(club);

        return post.getPostId();
    }
}
