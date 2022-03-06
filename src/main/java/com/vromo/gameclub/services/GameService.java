package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.GameDto;
import com.vromo.gameclub.dtos.GameRequestDto;

public interface GameService {

    GameDto save(GameRequestDto gameRequestDto);

    void remove(Long id);

}
