package com.project.gameclub.mappers;

import com.project.gameclub.dtos.GenreDto;
import com.project.gameclub.entities.Genre;
import org.mapstruct.Mapper;

@Mapper
public interface GenreMapper {

    GenreDto genreToGenreDto(Genre genre);
}
