package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.RollsDiceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RollsDiceUserRepository extends JpaRepository<RollsDiceUser, UUID> {
    Optional<RollsDiceUser> getRollsDiceUserByUsername(String username);
}
