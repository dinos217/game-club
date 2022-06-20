package com.project.gameclub.controllers;

import com.project.gameclub.dtos.GenreDto;
import com.project.gameclub.services.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping(value = "/all")
    Page<GenreDto> findAll(@RequestParam Integer page,
                           @RequestParam Integer pageSize,
                           @RequestParam String sortBy,
                           @RequestParam String direction) {

        logger.info("Started finding all genres paged");

        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, pageSize, sort);

        return genreService.findAll(pageable);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        genreService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
