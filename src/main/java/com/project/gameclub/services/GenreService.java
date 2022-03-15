package com.project.gameclub.services;

import com.project.gameclub.dtos.GenreDto;

public interface GenreService {

    GenreDto save(String genreName);

    void remove(Long id);
}
