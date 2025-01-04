package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.BoardGameCollection;
import com.unibuc.rolls_dice.entity.key.BoardGameCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameCollectionRepository extends JpaRepository<BoardGameCollection, BoardGameCollectionId> {
}
