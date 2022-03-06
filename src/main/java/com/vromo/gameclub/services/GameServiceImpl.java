package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.GameDto;
import com.vromo.gameclub.dtos.GameRequestDto;
import com.vromo.gameclub.entities.Game;
import com.vromo.gameclub.entities.Genre;
import com.vromo.gameclub.enums.GameStatus;
import com.vromo.gameclub.exceptions.ResourceAlreadyExistsException;
import com.vromo.gameclub.exceptions.ResourceNotFoundException;
import com.vromo.gameclub.mappers.GameGameDtoMapper;
import com.vromo.gameclub.repositories.GameRepository;
import com.vromo.gameclub.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepository;
    private GenreRepository genreRepository;
    private GameGameDtoMapper gameGameDtoMapper = Mappers.getMapper(GameGameDtoMapper.class);

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public GameDto save(GameRequestDto gameRequestDto) {

        Optional<Game> gameInDb = gameRepository.findByTitleAndStudio(gameRequestDto.getTitle(), gameRequestDto.getStudio());

        if (gameInDb.isPresent()) {
            throw new ResourceAlreadyExistsException("Game with title: " + gameRequestDto.getTitle() +
                    " and studio: " + gameRequestDto.getStudio() + " already exists.");
        }

        Game game = gameGameDtoMapper.gameDtoToGame(gameRequestDto);
        game.setIsLoaned(GameStatus.NOT_LOANED.getCode());
        game.setGenres(gameRequestDto.getGameGenres().stream()
                .map(genre -> genreRepository.findByGenreName(genre))
                .collect(Collectors.toList()));

        Game savedGame = gameRepository.save(game);
        GameDto gameDto = gameGameDtoMapper.gameToGameDto(savedGame);
        gameDto.setGameGenres(savedGame.getGenres().stream()
                .map(Genre::getGenreName).collect(Collectors.toList()));

        return gameDto;
    }

    @Override
    public void remove(Long id) {

        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            gameRepository.delete(game.get());
        } else {
            throw new ResourceNotFoundException("Could not find game with id: " + id);
        }
    }
}
