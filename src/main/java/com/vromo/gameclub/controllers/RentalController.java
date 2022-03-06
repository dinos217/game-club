package com.vromo.gameclub.controllers;

import com.vromo.gameclub.dtos.RentalDto;
import com.vromo.gameclub.dtos.RentalRequestDto;
import com.vromo.gameclub.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rentals")
public class RentalController {

    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(value = "/loan", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RentalDto> loanGame(@RequestBody RentalRequestDto rentalRequestDto) {

        RentalDto rentalDto = rentalService.loanGame(rentalRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(rentalDto);
    }

    @PostMapping(value = "/return", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RentalDto> returnGame(@RequestBody RentalRequestDto rentalRequestDto) {

        RentalDto rentalDto = rentalService.returnGame(rentalRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(rentalDto);
    }
}
