package com.vromo.gameclub.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rental")
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "game_id", nullable = false)
    private Long gameId;

//    private LocalDate startDate;
//
//    private LocalDate endDate;
//
//    private Double charge;

    @Column(name = "game_status", nullable = false)
    private String gameStatus; //todo: make it char and assign a char to enum ?

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
