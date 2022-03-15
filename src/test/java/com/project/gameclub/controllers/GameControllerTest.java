package com.project.gameclub.controllers;

import com.project.gameclub.repositories.GameRepository;
import com.project.gameclub.BasicTestConfig;
import com.project.gameclub.dtos.GameDto;
import com.project.gameclub.dtos.GameRequestDto;
import com.project.gameclub.exceptions.InvalidRequestException;
import com.project.gameclub.services.GameServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerTest extends BasicTestConfig {

    @Mock
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;

    @Test
    public void saveGameTest() throws Exception {

        Set genres = new HashSet<>();
        genres.add("Role Playing");

        GameRequestDto gameRequestDto = buildGameRequestDto(genres);
        GameDto gameDto = buildGameDto(genres);

        Mockito.when(gameService.save(gameRequestDto)).thenReturn(gameDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gameRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print());

        Mockito.verify(gameRepository, Mockito.times(2));
    }

    @Test
    public void saveGameAlreadyExistsTest() throws Exception {

        GameRequestDto gameRequestDto = buildExistingGameRequestDto();

        Mockito.when(gameService.save(gameRequestDto)).thenThrow(new InvalidRequestException(Mockito.any(String.class)));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(gameRequestDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isConflict())
                .andDo(print());
    }

    private GameDto buildGameDto(Set genres) {
        GameDto gameDto = new GameDto();
        gameDto.setId(10L);
        gameDto.setTitle("Pokemon Sapphire");
        gameDto.setStudio("Game Freak");
        gameDto.setGameGenres(genres);
        return gameDto;
    }

    private GameRequestDto buildGameRequestDto(Set genres) {
        GameRequestDto gameRequestDto = new GameRequestDto();
        gameRequestDto.setTitle("Pokemon Silver");
        gameRequestDto.setStudio("Game Freak");
        gameRequestDto.setGameGenres(genres);
        return gameRequestDto;
    }

    private GameRequestDto buildExistingGameRequestDto() {
        GameRequestDto gameRequestDto = new GameRequestDto();
        gameRequestDto.setTitle("Fifa 22");
        gameRequestDto.setStudio("EA Sports");
        gameRequestDto.setGameGenres(null);
        return gameRequestDto;
    }
}
