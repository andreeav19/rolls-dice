package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameCollectionRequestDto;
import com.unibuc.rolls_dice.entity.BoardGame;
import com.unibuc.rolls_dice.entity.BoardGameCollection;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.entity.key.BoardGameCollectionId;
import com.unibuc.rolls_dice.repository.BoardGameCollectionRepository;
import com.unibuc.rolls_dice.repository.BoardGameRepository;
import com.unibuc.rolls_dice.repository.RollsDiceUserRepository;
import com.unibuc.rolls_dice.type.CollectionType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardGameCollectionServiceImpl implements BoardGameCollectionService {
    private final BoardGameCollectionRepository boardGameCollectionRepository;
    private final RollsDiceUserRepository rollsDiceUserRepository;
    private final BoardGameRepository boardGameRepository;

    @Override
    public void addBoardGameToCollectionWithUsername(BoardGameCollectionRequestDto boardGameCollectionRequestDto) {
        String username = boardGameCollectionRequestDto.getUsername();
        Optional<RollsDiceUser> userOptional = rollsDiceUserRepository.getRollsDiceUserByUsername(username);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with username " + username + " not found.");
        }

        Long boardGameId = boardGameCollectionRequestDto.getBoardGameId();
        Optional<BoardGame> boardGameOptional = boardGameRepository.findById(boardGameId);
        if (boardGameOptional.isEmpty()) {
            throw new EntityNotFoundException("Board game with id " + boardGameId + " not found.");
        }

        Optional<BoardGameCollection> boardGameCollectionOptional = boardGameCollectionRepository
                .findById(new BoardGameCollectionId(userOptional.get().getUserId(), boardGameId));
        CollectionType collectionType = boardGameCollectionRequestDto.getCollectionType();
        BoardGameCollection boardGameCollection;

        if (boardGameCollectionOptional.isPresent()) {
            boardGameCollection = boardGameCollectionOptional.get();
        } else {
            boardGameCollection = new BoardGameCollection();
            boardGameCollection.setUser(userOptional.get());
            boardGameCollection.setBoardGame(boardGameOptional.get());
        }

        setCollectionType(boardGameCollection, collectionType);
        boardGameCollectionRepository.save(boardGameCollection);
    }

    private void setCollectionType(BoardGameCollection collection, CollectionType collectionType) {
        switch (collectionType) {
            case WANT_TO_BUY -> collection.setWantToBuy(true);
            case OWN -> {
                collection.setOwn(true);
                if (collection.getWantToBuy() != null && collection.getWantToBuy()) collection.setWantToBuy(false);
            }
            case RECOMMEND -> collection.setRecommend(true);
            default -> {}
        }
    }
}
