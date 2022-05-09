package com.project.gameclub.services;

import com.project.gameclub.dtos.GameRequestDto;
import com.project.gameclub.entities.Game;
import com.project.gameclub.entities.Genre;
import com.project.gameclub.exceptions.InvalidRequestException;
import com.project.gameclub.exceptions.ResourceNotFoundException;
import com.project.gameclub.repositories.GameRepository;
import com.project.gameclub.dtos.GameDto;
import com.project.gameclub.enums.GameStatus;
import com.project.gameclub.mappers.GameMapper;
import com.project.gameclub.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GameRepository gameRepository;
    private GenreRepository genreRepository;
    private GameMapper gameMapper = Mappers.getMapper(GameMapper.class);

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GenreRepository genreRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public GameDto save(GameRequestDto gameRequestDto) {

        Optional<Game> gameOptional = gameRepository.findByTitleAndStudio(gameRequestDto.getTitle(),
                gameRequestDto.getStudio());

        if (gameOptional.isPresent()) {
            logger.info("Game: " + gameRequestDto.getTitle() + " and studio: "
                    + gameRequestDto.getStudio() + " already exists.");
            throw new InvalidRequestException("Game with title: " + gameRequestDto.getTitle() +
                    " and studio: " + gameRequestDto.getStudio() + " already exists.");
        }

        //save genres that user entered which are not in db
        for (String genreName : gameRequestDto.getGameGenres()) {
            if (genreRepository.findByGenreName(genreName).isEmpty()) {
                logger.info("Saved genre: " + genreName + " which did not exist in db.");
                genreRepository.save(new Genre(genreName));
            }
        }

        Game gameToBeSaved = buildGameToBeSaved(gameRequestDto);
        Game savedGame = gameRepository.save(gameToBeSaved);
        logger.info("SUCCESS: Game with title: " + gameRequestDto.getTitle() + " and studio: "
                + gameRequestDto.getStudio() + " saved in database.");

        GameDto gameDto = gameMapper.gameToGameDto(savedGame);
        gameDto.setGameGenres(savedGame.getGenres().stream()
                .map(Genre::getGenreName)
                .collect(Collectors.toCollection(HashSet::new)));

        return gameDto;
    }

    @Override
    public void delete(Long id) {

        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            logger.info("SUCCESS: Game with id: " + id + " deleted from database.");
            gameRepository.delete(gameOptional.get());
        } else {
            throw new ResourceNotFoundException("Could not find game with id: " + id);
        }
    }

    private Game buildGameToBeSaved(GameRequestDto gameRequestDto) {
        Game gameToBeSaved = gameMapper.gameDtoToGame(gameRequestDto);
        gameToBeSaved.setIsLoaned(GameStatus.NOT_LOANED.getCode());

        gameToBeSaved.setGenres(gameRequestDto.getGameGenres().stream()
                .map(genre -> genreRepository.findByGenreName(genre).orElse(null))
                .collect(Collectors.toCollection(HashSet::new)));
        return gameToBeSaved;
    }
}
