package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.GenreDto;
import com.vromo.gameclub.entities.Genre;
import com.vromo.gameclub.exceptions.ResourceNotFoundException;
import com.vromo.gameclub.mappers.GenreMapper;
import com.vromo.gameclub.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    private GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public GenreDto save(String genreName) {
        return genreMapper.genreToGenreDto(genreRepository.save(new Genre(genreName)));
    }

    @Override
    public void remove(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isPresent()) {
            genreRepository.delete(genreOptional.get());
        } else {
            throw new ResourceNotFoundException("Could not find genre with id: " + id);
        }
    }
}
