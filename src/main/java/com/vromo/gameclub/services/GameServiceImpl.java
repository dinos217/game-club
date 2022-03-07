package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.GameDto;
import com.vromo.gameclub.dtos.GameRequestDto;
import com.vromo.gameclub.entities.Game;
import com.vromo.gameclub.entities.Genre;
import com.vromo.gameclub.enums.GameStatus;
import com.vromo.gameclub.exceptions.InvalidRequestException;
import com.vromo.gameclub.exceptions.ResourceNotFoundException;
import com.vromo.gameclub.mappers.GameMapper;
import com.vromo.gameclub.repositories.GameRepository;
import com.vromo.gameclub.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

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
            throw new InvalidRequestException("Game with title: " + gameRequestDto.getTitle() +
                    " and studio: " + gameRequestDto.getStudio() + " already exists.");
        }

        Game gameToBeSaved = buildGameToBeSaved(gameRequestDto);
        Game savedGame = gameRepository.save(gameToBeSaved);

        GameDto gameDto = gameMapper.gameToGameDto(savedGame);
        gameDto.setGameGenres(savedGame.getGenres().stream()
                .map(Genre::getGenreName)
                .collect(Collectors.toCollection(HashSet::new)));

        return gameDto;
    }

    @Override
    public void remove(Long id) {

        Optional<Game> gameOptional = gameRepository.findById(id);

        if (gameOptional.isPresent()) {
            gameRepository.delete(gameOptional.get());
        } else {
            throw new ResourceNotFoundException("Could not find game with id: " + id);
        }
    }

    private Game buildGameToBeSaved(GameRequestDto gameRequestDto) {
        Game gameToBeSaved = gameMapper.gameDtoToGame(gameRequestDto);
        gameToBeSaved.setIsLoaned(GameStatus.NOT_LOANED.getCode());

        //save genres that user entered which are not in db
        for (String genreName : gameRequestDto.getGameGenres()) {
            if (genreRepository.findByGenreName(genreName).isEmpty()) {
                genreRepository.save(new Genre(genreName));
            }
        }

        gameToBeSaved.setGenres(gameRequestDto.getGameGenres().stream()
                .map(genre -> genreRepository.findByGenreName(genre).orElse(null))
                .collect(Collectors.toCollection(HashSet::new)));
        return gameToBeSaved;
    }
}
