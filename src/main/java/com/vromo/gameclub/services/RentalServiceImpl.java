package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.RentalDto;
import com.vromo.gameclub.dtos.RentalRequestDto;
import com.vromo.gameclub.entities.Game;
import com.vromo.gameclub.entities.Rental;
import com.vromo.gameclub.exceptions.ResourceNotFoundException;
import com.vromo.gameclub.repositories.GameRepository;
import com.vromo.gameclub.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private GameRepository gameRepository;
    private RentalRepository rentalRepository;

    @Autowired
    public RentalServiceImpl(GameRepository gameRepository, RentalRepository rentalRepository) {
        this.gameRepository = gameRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public RentalDto loanGame(RentalRequestDto requestDto) {

        Optional<Game> gameInDb = gameRepository.findById(requestDto.getGameId());

        if (gameInDb.isEmpty())
            throw new ResourceNotFoundException("Game with id: " + requestDto.getGameId() + " does not exist in database.");




        return null;
    }

    @Override
    public RentalDto returnGame(RentalRequestDto rentalRequestDto) {
        return null;
    }
}
