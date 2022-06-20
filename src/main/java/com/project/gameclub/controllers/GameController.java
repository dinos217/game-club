package com.project.gameclub.controllers;

import com.project.gameclub.services.GameService;
import com.project.gameclub.dtos.GameDto;
import com.project.gameclub.dtos.GameRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping(value = "/all")
    Page<GameDto> findAll(@RequestParam Integer page,
                          @RequestParam Integer pageSize,
                          @RequestParam String sortBy,
                          @RequestParam String direction) {

        logger.info("Started finding all games paged...");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return gameService.findAll(pageable);
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

        gameService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
