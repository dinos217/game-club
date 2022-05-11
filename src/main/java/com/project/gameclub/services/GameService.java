package com.project.gameclub.services;

import com.project.gameclub.dtos.GameDto;
import com.project.gameclub.dtos.GameRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameService {

    GameDto save(GameRequestDto gameRequestDto);

    void delete(Long id);

    Page<GameDto> findAll(Pageable pageable);

}
