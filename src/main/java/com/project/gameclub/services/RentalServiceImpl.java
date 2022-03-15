package com.project.gameclub.services;

import com.project.gameclub.dtos.RentalRequestDto;
import com.project.gameclub.entities.Game;
import com.project.gameclub.entities.Rental;
import com.project.gameclub.exceptions.BadRequestException;
import com.project.gameclub.exceptions.InvalidRequestException;
import com.project.gameclub.exceptions.ResourceNotFoundException;
import com.project.gameclub.mappers.RentalMapper;
import com.project.gameclub.repositories.GameRepository;
import com.project.gameclub.repositories.RentalRepository;
import com.project.gameclub.dtos.RentalDto;
import com.project.gameclub.enums.GameStatus;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
            logger.info("Game with id: " + requestDto.getGameId() + " does not exist in database.");
            throw new ResourceNotFoundException("Game with id: " + requestDto.getGameId()
                    + " does not exist in database.");
        }

        Game game = gameOptional.get();

        if (game.getIsLoaned().equals(GameStatus.LOANED.getCode())) {
            logger.info("Game with id: " + requestDto.getGameId() + " is not available.");
            throw new BadRequestException("Game with id: " + requestDto.getGameId() + " is not available.");
        }

        Short rentalsCount = rentalRepository.countByMemberId(requestDto.getMemberId());

        if (rentalsCount < MAX_RENTALS) {
            Rental rental = rentalMapper.rentalRequestDtoToRental(requestDto);
            rentalRepository.save(rental);

            //update game record with proper game status
            game.setIsLoaned(GameStatus.LOANED.getCode());
            gameRepository.save(game);

            logger.info("SUCCESS: Member: " + requestDto.getMemberId() + " just loaned game: " + game.getTitle());
            return rentalMapper.rentalToRentalDto(rental);

        } else {
            logger.info("Member with id: " + requestDto.getMemberId() + " has reached the maximum number of rentals");
            throw new InvalidRequestException("Member with id: " + requestDto.getMemberId()
                    + "has reached the maximum number of rentals (3 games)");
        }
    }

    @Override
    public RentalDto returnGame(RentalRequestDto requestDto) {

        Optional<Rental> rental = rentalRepository.
                findByMemberIdAndGameId(requestDto.getMemberId(), requestDto.getGameId());

        if (rental.isPresent()) {
            rentalRepository.delete(rental.get());

            //update game record with proper game status
            Game game = gameRepository.getById(requestDto.getGameId());
            game.setIsLoaned(GameStatus.NOT_LOANED.getCode());
            gameRepository.save(game);

            logger.info("SUCCESS: Member: " + requestDto.getMemberId() + " just returned game: " + game.getTitle());
            return rentalMapper.rentalToRentalDto(rental.get());
        } else {
            logger.info("Member: " + requestDto.getMemberId() + " has not rented game: "
                    + requestDto.getMemberId());
            throw new InvalidRequestException("Member " + requestDto.getMemberId() + " has not rented game: "
                    + requestDto.getMemberId());
        }
    }
}
