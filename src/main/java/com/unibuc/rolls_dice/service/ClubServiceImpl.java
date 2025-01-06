package com.unibuc.rolls_dice.service;

import com.unibuc.rolls_dice.dto.BoardGameResponseDto;
import com.unibuc.rolls_dice.dto.ClubRequestDto;
import com.unibuc.rolls_dice.dto.ClubResponseDto;
import com.unibuc.rolls_dice.entity.BoardGame;
import com.unibuc.rolls_dice.entity.Category;
import com.unibuc.rolls_dice.entity.Club;
import com.unibuc.rolls_dice.entity.RollsDiceUser;
import com.unibuc.rolls_dice.repository.BoardGameRepository;
import com.unibuc.rolls_dice.repository.ClubRepository;
import com.unibuc.rolls_dice.repository.RollsDiceUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final RollsDiceUserRepository rollsDiceUserRepository;
    private final DatabaseLookup databaseLookup;
    private final BoardGameRepository boardGameRepository;

    private List<ClubResponseDto> mapClubsToDtoList(List<Club> clubs) {
        return clubs
                .stream()
                .map(club -> {
                    return new ClubResponseDto(
                            club.getClubId(),
                            club.getName(),
                            club.getDescription(),
                            club.getLeader().getUsername(),
                            club.getMaxMembers(),
                            club.getBoardGame() != null ?
                                    new BoardGameResponseDto(
                                            club.getBoardGame().getName(),
                                            club.getBoardGame().getRatingScore(),
                                            club.getBoardGame().getDescription(),
                                            club.getBoardGame().getRulesLink(),
                                            club.getBoardGame().getCategoryList().stream()
                                                    .map(Category::getName)
                                                    .toList()
                                    )
                                    : null,
                            club.getUserList().stream()
                                    .map(RollsDiceUser::getUsername)
                                    .toList()
                    );
                })
                .toList();
    }

    public List<ClubResponseDto> getClubsByName(String clubName) {
        List<Club> clubs = clubRepository.findClubsByNameContainingIgnoreCase(clubName);
        return mapClubsToDtoList(clubs);
    }

    public List<ClubResponseDto> getClubsByCategories(List<Integer> categories) {
        List<Club> clubs = clubRepository.findClubsByBoardGame_CategoryList_CategoryIdIn(categories);
        return mapClubsToDtoList(clubs);
    }

    public Long addClub(ClubRequestDto clubRequestDto) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(clubRequestDto.getUsername());

        Club club = new Club();
        club.setName(clubRequestDto.getName());
        club.setDescription(clubRequestDto.getDescription());
        club.setMaxMembers(clubRequestDto.getMaxMembers());
        club.setLeader(user);
        club.setUserList(new ArrayList<>(List.of(user)));
        clubRepository.save(club);

        user.setJoinedClubList(new ArrayList<>(List.of(club)));
        user.setLedClubList(new ArrayList<>(List.of(club)));
        rollsDiceUserRepository.save(user);

        return club.getClubId();
    }

    public void addBoardGameToClub(String username, Long clubId, Long boardGameId) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);
        Club club = databaseLookup.retrieveClubById(clubId);
        BoardGame boardGame = databaseLookup.retrieveBoardGameById(boardGameId);

        databaseLookup.checkUserIsLeaderOfClub(user, club);
        databaseLookup.checkClubBoardGameIsNotSet(club);

        club.setBoardGame(boardGame);
        clubRepository.save(club);

        if (boardGame.getClubList() == null) {
            boardGame.setClubList(new ArrayList<>(List.of(club)));
        } else {
            boardGame.getClubList().add(club);
        }
        boardGameRepository.save(boardGame);
    }

    public void editClubBoardGame(String username, Long clubId, Long boardGameId) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);
        Club club = databaseLookup.retrieveClubById(clubId);
        BoardGame boardGame = databaseLookup.retrieveBoardGameById(boardGameId);

        databaseLookup.checkUserIsLeaderOfClub(user, club);
        databaseLookup.checkClubBoardGameIsSet(club);

        club.setBoardGame(boardGame);
        clubRepository.save(club);

        boardGame.getClubList().removeIf(existingClub -> existingClub.getClubId().equals(club.getClubId()));
        if (boardGame.getClubList() == null) {
            boardGame.setClubList(new ArrayList<>(List.of(club)));
        } else {
            boardGame.getClubList().add(club);
        }
        boardGameRepository.save(boardGame);
    }

    public void editClub(Long clubId, ClubRequestDto clubRequestDto) {
        databaseLookup.retrieveUserByUsername(clubRequestDto.getUsername());
        Club club = databaseLookup.retrieveClubById(clubId);

        long membersCount = club.getUserList().size();
        if (clubRequestDto.getMaxMembers() < membersCount) {
            throw new IllegalStateException("Maximum limit for members from club with id " + clubId +
                    " should be equal to or greater than the number of existing members: " + membersCount + ".");
        }

        club.setName(clubRequestDto.getName());
        club.setDescription(clubRequestDto.getDescription());
        club.setMaxMembers(clubRequestDto.getMaxMembers());
        clubRepository.save(club);
    }

    public void addUserToClubMembers(String username, Long clubId) {
        RollsDiceUser user = databaseLookup.retrieveUserByUsername(username);
        Club club = databaseLookup.retrieveClubById(clubId);

        if (club.getMaxMembers() != null) {
            long membersCount = club.getUserList().size();
            if (membersCount == club.getMaxMembers()) {
                throw new IllegalStateException("Maximum limit for members from club with id " + clubId + " exceeded.");
            }
            boolean userExists = club.getUserList().stream()
                    .anyMatch(existingUser -> existingUser.getUserId().equals(user.getUserId()));
            if (userExists) {
                return;
            }
        }

        club.getUserList().add(user);
        clubRepository.save(club);

        user.getJoinedClubList().add(club);
        rollsDiceUserRepository.save(user);
    }

    public void deleteClubById(Long clubId) {
        Club club = databaseLookup.retrieveClubById(clubId);

        club.getLeader().getLedClubList().remove(club);
        club.getUserList().forEach(user -> user.getJoinedClubList().remove(club));
        club.getUserList().clear();

        if (club.getBoardGame() != null)
            club.getBoardGame().getClubList().remove(club);

        clubRepository.delete(club);
    }
}
