package com.project.gameclub.services;

import com.project.gameclub.dtos.GameDto;
import com.project.gameclub.dtos.GameRequestDto;

public interface GameService {

    GameDto save(GameRequestDto gameRequestDto);

    void delete(Long id);

}
