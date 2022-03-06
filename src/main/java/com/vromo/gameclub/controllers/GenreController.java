package com.vromo.gameclub.controllers;

import com.vromo.gameclub.dtos.GenreDto;
import com.vromo.gameclub.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<GenreDto> save(@RequestParam String genreName) {

        GenreDto genreDto = genreService.save(genreName);
        return ResponseEntity.status(HttpStatus.OK).body(genreDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        genreService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
