package com.vromo.gameclub.repositories;

import com.vromo.gameclub.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Short countByMemberId(Long memberId);

    Rental findByMemberIdAndGameId(Long memberId, Long gameId);
}
