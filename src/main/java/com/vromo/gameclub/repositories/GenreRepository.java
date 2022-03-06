package com.vromo.gameclub.repositories;

import com.vromo.gameclub.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByGenreName(String name);
}
