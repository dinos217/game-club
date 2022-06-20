package com.project.gameclub.services;

import com.project.gameclub.dtos.GenreDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    GenreDto save(String genreName);

    void delete(Long id);

    Page<GenreDto> findAll(Pageable pageable);
}
