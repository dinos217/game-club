package com.vromo.gameclub.repositories;

import com.vromo.gameclub.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findByTitleAndStudio(String name, String studio);
}
