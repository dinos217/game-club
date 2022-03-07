package com.vromo.gameclub.controllers;

import com.vromo.gameclub.dtos.GameDto;
import com.vromo.gameclub.dtos.GameRequestDto;
import com.vromo.gameclub.services.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GameDto> save(@RequestBody GameRequestDto gameRequestDto) {

        logger.info("Started saving game in database...");

        GameDto gameDto = gameService.save(gameRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(gameDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        logger.info("Started deleting game from database...");

        gameService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
