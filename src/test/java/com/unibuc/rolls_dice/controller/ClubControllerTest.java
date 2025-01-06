package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.ClubRequestDto;
import com.unibuc.rolls_dice.service.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ClubController.class)
@ExtendWith(MockitoExtension.class)
class ClubControllerTest {
    @Autowired
    private ClubController clubController;

    @MockitoBean
    private ClubService clubService;

    private ClubRequestDto requestDto;
    private Long clubId;

    @BeforeEach
    void setUp() {
        clubId = 11L;
        requestDto = new ClubRequestDto(
                "mockUser",
                "clubName",
                "description",
                2
        );
    }

    @Test
    void testEditClubWithValidParameters() {
        doNothing().when(clubService).editClub(clubId, requestDto);
        clubController.editClub(clubId, requestDto);
        verify(clubService, times(1)).editClub(clubId, requestDto);
    }
}