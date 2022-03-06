package com.vromo.gameclub.dtos;

import lombok.Builder;

@Builder
public class RentalDto {

    private Long id;
    private Long memberId;
    private Long gameId;
//    private LocalDate startDate;
//    private LocalDate endDate;
//    private Double charge;
    private String gameStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}
