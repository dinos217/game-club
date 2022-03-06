package com.vromo.gameclub.dtos;

import lombok.Builder;

@Builder
public class RentalRequestDto {

    private Long memberId;
    private Long gameId;
//    private LocalDate startDate;
//    private LocalDate plannedEndDate;
//    private LocalDate actualEndDate;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getPlannedEndDate() {
//        return plannedEndDate;
//    }
//
//    public void setPlannedEndDate(LocalDate plannedEndDate) {
//        this.plannedEndDate = plannedEndDate;
//    }
//
//    public LocalDate getActualEndDate() {
//        return actualEndDate;
//    }
//
//    public void setActualEndDate(LocalDate actualEndDate) {
//        this.actualEndDate = actualEndDate;
//    }
}
