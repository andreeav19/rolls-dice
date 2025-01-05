package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.BoardGameCollection;
import com.unibuc.rolls_dice.entity.key.BoardGameCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGameCollectionRepository extends JpaRepository<BoardGameCollection, BoardGameCollectionId> {
    List<BoardGameCollection> findAllByBoardGame_BoardGameId(Long boardGameId);
}
