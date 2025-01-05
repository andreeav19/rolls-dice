package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
    List<Club> getClubsByNameContainingIgnoreCase(String clubName);
}
