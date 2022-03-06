package com.vromo.gameclub.mappers;

import com.vromo.gameclub.dtos.GenreDto;
import com.vromo.gameclub.entities.Genre;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    GenreDto genreToGenreDto(Genre genre);
    Genre genreDtoToGenre(GenreDto genreDto);
}
