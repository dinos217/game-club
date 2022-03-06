package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.RentalDto;
import com.vromo.gameclub.dtos.RentalRequestDto;
import com.vromo.gameclub.entities.Game;
import com.vromo.gameclub.entities.Rental;
import com.vromo.gameclub.enums.GameStatus;
import com.vromo.gameclub.exceptions.BadRequestException;
import com.vromo.gameclub.exceptions.InvalidRequestException;
import com.vromo.gameclub.exceptions.ResourceNotFoundException;
import com.vromo.gameclub.mappers.RentalMapper;
import com.vromo.gameclub.repositories.GameRepository;
import com.vromo.gameclub.repositories.RentalRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private static final Short MAX_RENTALS = 3;

    private GameRepository gameRepository;
    private RentalRepository rentalRepository;
    private RentalMapper rentalMapper = Mappers.getMapper(RentalMapper.class);

    @Autowired
    public RentalServiceImpl(GameRepository gameRepository, RentalRepository rentalRepository) {
        this.gameRepository = gameRepository;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public RentalDto loanGame(RentalRequestDto requestDto) {

        Optional<Game> gameOptional = gameRepository.findById(requestDto.getGameId());

        if (gameOptional.isEmpty()) {
            throw new ResourceNotFoundException("Game with id: " + requestDto.getGameId()
                    + " does not exist in database.");
        }

        Game game = gameOptional.get();

        if (game.getIsLoaned().equals(GameStatus.LOANED.getCode())) {
            throw new BadRequestException("Game with id: " + requestDto.getGameId() + " is not available.");
        }

        Short rentalsCount = rentalRepository.countByMemberIdAndGameStatus(requestDto.getMemberId(),
                GameStatus.LOANED.getCode());

        if (rentalsCount < MAX_RENTALS) {
            Rental rental = rentalMapper.rentalRequestDtoToRental(requestDto);
            rental.setGameStatus(GameStatus.LOANED.getCode());
            rentalRepository.save(rental);

            //update game record with proper game status
            game.setIsLoaned(GameStatus.LOANED.getCode());
            gameRepository.save(game);

            return rentalMapper.rentalToRentalDto(rental);

        } else {
            throw new InvalidRequestException("Member with id: " + requestDto.getMemberId()
                    + "has reached the maximum number of rentals (3 games)");
        }
    }

    @Override
    public RentalDto returnGame(RentalRequestDto rentalRequestDto) {

        Rental rental = rentalMapper.rentalRequestDtoToRental(rentalRequestDto);
        rental.setGameStatus(GameStatus.NOT_LOANED.getCode());
        rentalRepository.save(rental);

        //update game record with proper game status
        Game game = gameRepository.getById(rentalRequestDto.getGameId());
        game.setIsLoaned(GameStatus.NOT_LOANED.getCode());
        gameRepository.save(game);

        return rentalMapper.rentalToRentalDto(rental);
    }
}
