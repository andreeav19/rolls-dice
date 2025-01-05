package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.entity.BoardGame;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.Post;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.BoardGameRepository;
import com.unibuc.rolls_dice.repository.ClubRepository;
import com.unibuc.rolls_dice.repository.PostRepository;
import com.unibuc.rolls_dice.repository.RollsDiceUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class DatabaseLookupImpl implements DatabaseLookup {
    private final RollsDiceUserRepository rollsDiceUserRepository;
    private final BoardGameRepository boardGameRepository;
    private final ClubRepository clubRepository;
    private final PostRepository postRepository;

    public RollsDiceUser retrieveUserByUsername(String username) {
        Optional<RollsDiceUser> userOptional = rollsDiceUserRepository.getRollsDiceUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with username " + username + " not found.");
        }
        return userOptional.get();
    }

    public BoardGame retrieveBoardGameById(Long boardGameId) {
        Optional<BoardGame> boardGameOptional = boardGameRepository.findById(boardGameId);
        if (boardGameOptional.isEmpty()) {
            throw new EntityNotFoundException("Board game with id " + boardGameId + " not found.");
        }
        return boardGameOptional.get();
    }

    public Club retrieveClubById(Long clubId) {
        Optional<Club> clubOptional = clubRepository.findById(clubId);
        if (clubOptional.isEmpty()) {
            throw new EntityNotFoundException("Club with id " + clubId + " not found.");
        }
        return clubOptional.get();
    }

    public Post retrievePostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new EntityNotFoundException("Post with id " + postId + " not found.");
        }
        return postOptional.get();
    }

    public void checkUserIsMemberOfClub(RollsDiceUser user, Club club) {
        boolean userExists = club.getUserList().stream()
                .anyMatch(existingUser -> existingUser.getUserId().equals(user.getUserId()));
        if (!userExists) {
            throw new IllegalStateException("User " + user.getUsername()
                    + " is not a member of the club with the id " + club.getClubId());
        }
    }

    public void checkUserIsLeaderOfClub(RollsDiceUser user, Club club) {
        if (club.getLeader() != user) {
            throw new IllegalStateException("User " + user.getUsername()
                    + " is not the leader of the club with the id " + club.getClubId());
        }
    }

    public void checkClubBoardGameIsNotSet(Club club) {
        if (club.getBoardGame() != null) {
            throw new IllegalStateException("Error Creating: Club with id " + club.getClubId()
                    + " already has board game set.");
        }
    }

    public void checkClubBoardGameIsSet(Club club) {
        if (club.getBoardGame() == null) {
            throw new IllegalStateException("Error Updating: Club with id " + club.getClubId()
                    + " does not have board game set.");
        }
    }
}
