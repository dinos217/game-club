package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.RentalDto;
import com.vromo.gameclub.dtos.RentalRequestDto;

public interface RentalService {

    RentalDto loanGame(RentalRequestDto rentalRequestDto);

    RentalDto returnGame(RentalRequestDto rentalRequestDto);
}
