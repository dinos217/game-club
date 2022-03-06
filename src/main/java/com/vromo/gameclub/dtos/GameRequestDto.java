package com.vromo.gameclub.dtos;

import java.util.List;

public class GameRequestDto {

    private String title;
    private String studio; //todo: make it enum?
    private List<String> gameGenres;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public List<String> getGameGenres() {
        return gameGenres;
    }

    public void setGameGenres(List<String> gameGenres) {
        this.gameGenres = gameGenres;
    }
}
