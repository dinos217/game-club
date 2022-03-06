package com.vromo.gameclub.controllers;

import com.vromo.gameclub.dtos.GameDto;
import com.vromo.gameclub.dtos.GameRequestDto;
import com.vromo.gameclub.dtos.RentalDto;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RentalDto> save(@RequestBody GameRequestDto gameRequestDto) {

//        GameDto gameDto = rentalService.save(gameRequestDto);
        return null;
    }
}
