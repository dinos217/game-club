package com.vromo.gameclub.services;

import com.vromo.gameclub.dtos.GenreDto;

public interface GenreService {

    GenreDto save(String genreName);

    void remove(Long id);
}
