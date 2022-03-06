package com.vromo.gameclub.mappers;

import com.vromo.gameclub.dtos.GameDto;
import com.vromo.gameclub.dtos.GameRequestDto;
import com.vromo.gameclub.entities.Game;
import org.mapstruct.Mapper;

@Mapper
public interface GameGameDtoMapper {

    GameDto gameToGameDto(Game game);
    Game gameDtoToGame(GameRequestDto gameDto);
}
