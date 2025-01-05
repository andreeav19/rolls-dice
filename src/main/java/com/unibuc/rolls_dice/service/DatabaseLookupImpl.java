package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.entity.BoardGame;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.BoardGameRepository;
import com.unibuc.rolls_dice.repository.ClubRepository;
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
}
