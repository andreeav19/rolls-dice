package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.entity.BoardGame;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.Post;
import com.unibuc.rolls_dice.entity.RollsDiceUser;

interface DatabaseLookup {
    RollsDiceUser retrieveUserByUsername(String username);
    BoardGame retrieveBoardGameById(Long boardGameId);
    Club retrieveClubById(Long clubId);
    Post retrievePostById(Long postId);
    void checkUserIsMemberOfClub(RollsDiceUser user, Club club);
}
