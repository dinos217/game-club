package com.vromo.gameclub.mappers;

import com.vromo.gameclub.dtos.RentalDto;
import com.vromo.gameclub.dtos.RentalRequestDto;
import com.vromo.gameclub.entities.Rental;
import org.mapstruct.Mapper;

@Mapper
public interface RentalMapper {

    RentalDto rentalToRentalDto(Rental rental);
    Rental rentalDtoToRental(RentalDto rentalDto);
    Rental rentalRequestDtoToRental(RentalRequestDto rentalDto);
}
