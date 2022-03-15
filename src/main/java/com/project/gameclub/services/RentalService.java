package com.project.gameclub.services;

import com.project.gameclub.dtos.RentalRequestDto;
import com.project.gameclub.dtos.RentalDto;

public interface RentalService {

    RentalDto loanGame(RentalRequestDto rentalRequestDto);

    RentalDto returnGame(RentalRequestDto rentalRequestDto);
}
