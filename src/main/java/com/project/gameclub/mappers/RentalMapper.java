package com.project.gameclub.mappers;

import com.project.gameclub.dtos.RentalDto;
import com.project.gameclub.dtos.RentalRequestDto;
import com.project.gameclub.entities.Rental;
import org.mapstruct.Mapper;

@Mapper
public interface RentalMapper {

    RentalDto rentalToRentalDto(Rental rental);
    Rental rentalRequestDtoToRental(RentalRequestDto rentalDto);
}
