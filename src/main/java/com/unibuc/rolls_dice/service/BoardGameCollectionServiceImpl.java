package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameCollectionRequestDto;
import com.unibuc.rolls_dice.dto.RatingRequestDto;
import com.unibuc.rolls_dice.entity.BoardGame;
import com.unibuc.rolls_dice.entity.BoardGameCollection;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.entity.key.BoardGameCollectionId;
import com.unibuc.rolls_dice.repository.BoardGameCollectionRepository;
import com.unibuc.rolls_dice.repository.BoardGameRepository;
import com.unibuc.rolls_dice.type.CollectionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardGameCollectionServiceImpl implements BoardGameCollectionService {
    private final BoardGameCollectionRepository boardGameCollectionRepository;
    private final DatabaseLookup databaseLookup;
    private final BoardGameRepository boardGameRepository;

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

    private float recalculateBoardGameRating(Long boardGameId) {
        List<BoardGameCollection> collections =
                boardGameCollectionRepository.findAllByBoardGame_BoardGameId(boardGameId);
        long boardGameCount = collections.size();
        double scoreSum = collections.stream()
                .mapToDouble(BoardGameCollection::getRatingScore)
                .sum();

        return (float) (scoreSum / boardGameCount);
    }

    private BoardGameCollection retrieveOrCreateBoardGameCollection(RollsDiceUser user, BoardGame boardGame) {
        Optional<BoardGameCollection> boardGameCollectionOptional = boardGameCollectionRepository
                .findById(new BoardGameCollectionId(user.getUserId(), boardGame.getBoardGameId()));
        if (boardGameCollectionOptional.isPresent()) {
            return boardGameCollectionOptional.get();
        }

        BoardGameCollection boardGameCollection = new BoardGameCollection();
        boardGameCollection.setUser(user);
        boardGameCollection.setBoardGame(boardGame);
        return boardGameCollection;
    }

    @Override
    public void addBoardGameToCollectionWithUsername(BoardGameCollectionRequestDto boardGameCollectionRequestDto) {
        String username = boardGameCollectionRequestDto.getUsername();
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);

        Long boardGameId = boardGameCollectionRequestDto.getBoardGameId();
        BoardGame boardGame = databaseLookup.retrieveBoardGameById(boardGameId);

        CollectionType collectionType = boardGameCollectionRequestDto.getCollectionType();
        BoardGameCollection boardGameCollection = retrieveOrCreateBoardGameCollection(user, boardGame);

        setCollectionType(boardGameCollection, collectionType);
        boardGameCollectionRepository.save(boardGameCollection);
    }

    public void addRatingToBoardGame(RatingRequestDto ratingRequestDto) {
        String username = ratingRequestDto.getUsername();
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);

        Long boardGameId = ratingRequestDto.getBoardGameId();
        BoardGame boardGame = databaseLookup.retrieveBoardGameById(boardGameId);

        BoardGameCollection boardGameCollection = retrieveOrCreateBoardGameCollection(user, boardGame);
        boardGameCollection.setRatingScore(ratingRequestDto.getRatingScore());
        boardGameCollection.setRatingDescription(ratingRequestDto.getRatingDescription());
        boardGameCollectionRepository.save(boardGameCollection);

        float ratingScore = recalculateBoardGameRating(boardGameId);
        boardGame.setRatingScore(ratingScore);
        boardGameRepository.save(boardGame);
    }
}
