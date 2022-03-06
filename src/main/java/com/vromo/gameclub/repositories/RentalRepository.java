package com.vromo.gameclub.repositories;

import com.vromo.gameclub.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Short countByMemberIdAndGameStatus(Long memberId, Short gameStatus);
}
