package com.project.gameclub.services;

import com.project.gameclub.dtos.GenreDto;
import com.project.gameclub.entities.Genre;
import com.project.gameclub.exceptions.BadRequestException;
import com.project.gameclub.exceptions.ResourceNotFoundException;
import com.project.gameclub.mappers.GenreMapper;
import com.project.gameclub.repositories.GenreRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public Page<GenreDto> findAll(Pageable pageable) {

        Page<Genre> genresFromDb = genreRepository.findAll(pageable);
        long total = 0L;

        if (!ObjectUtils.isEmpty(genresFromDb)) total = genreRepository.count();

        List<GenreDto> genres = genresFromDb.stream()
                .map(genre -> genreMapper.genreToGenreDto(genre))
                .collect(Collectors.toList());

        return new PageImpl<>(genres, pageable, total);
    }

    @Override
    public void delete(Long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);

        if (genreOptional.isPresent()) {
            try {
                genreRepository.delete(genreOptional.get());
            } catch (Exception e) {
                logger.info(e.getMessage());
                throw new BadRequestException(e.getMessage());
            }
        } else {
            throw new ResourceNotFoundException("Could not find genre with id: " + id);
        }
    }
}
