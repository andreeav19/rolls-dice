package com.unibuc.rolls_dice.controller;

import com.unibuc.rolls_dice.dto.EventRequestDto;
import com.unibuc.rolls_dice.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = EventController.class)
@ExtendWith(MockitoExtension.class)
class EventControllerTest {
    @Autowired
    private EventController eventController;

    @MockitoBean
    private EventService eventService;

    @Test
    void testAddEvent() {
        Long clubId = 1L;
        EventRequestDto requestDto = new EventRequestDto(
                "username",
                "name",
                "description",
                "location",
                LocalDateTime.now(),
                1
        );
        Long eventId = 1L;
        when(eventService.addEvent(clubId, requestDto)).thenReturn(eventId);
        ResponseEntity<Long> result = eventController.addEvent(clubId, requestDto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(eventId, result.getBody());
    }

    @Test
    void testDeleteEventById() {
        Long eventId = 1L;
        String username = "username";
        doNothing().when(eventService).deleteEventById(eventId, username);
        eventController.deleteEventById(eventId, username);
        verify(eventService, times(1)).deleteEventById(eventId, username);
    }
}