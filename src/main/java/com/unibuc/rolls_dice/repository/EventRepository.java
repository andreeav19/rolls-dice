package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findEventsByClub_ClubId(Long clubId);
}
