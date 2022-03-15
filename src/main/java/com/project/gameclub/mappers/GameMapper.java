package com.project.gameclub.mappers;

import com.project.gameclub.dtos.GameDto;
import com.project.gameclub.dtos.GameRequestDto;
import com.project.gameclub.entities.Game;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    GameDto gameToGameDto(Game game);
    Game gameDtoToGame(GameRequestDto gameDto);
}
