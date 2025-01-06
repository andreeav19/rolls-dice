package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.entity.*;

interface DatabaseLookup {
    RollsDiceUser retrieveUserByUsername(String username);
    BoardGame retrieveBoardGameById(Long boardGameId);
    Club retrieveClubById(Long clubId);
    Post retrievePostById(Long postId);
    Event retrieveEventById(Long eventId);
    void checkUserIsMemberOfClub(RollsDiceUser user, Club club);
    void checkUserIsLeaderOfClub(RollsDiceUser user, Club club);
    void checkUserIsAttendeeOfEvent(RollsDiceUser user, Event event);
    void checkClubBoardGameIsNotSet(Club club);
    void checkClubBoardGameIsSet(Club club);
    void checkEventWinnerIsNotSet(Event event);
}
