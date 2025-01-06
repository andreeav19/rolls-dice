package com.unibuc.rolls_dice.repository;

import com.unibuc.rolls_dice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostsByClub_ClubId(Long clubId);
}
