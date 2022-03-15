package com.project.gameclub.dtos;

import java.util.Set;

public class GameDto {

    private Long id;
    private String title;
    private String studio;
    private Set<String> gameGenres;

    public GameDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<String> getGameGenres() {
        return gameGenres;
    }

    public void setGameGenres(Set<String> gameGenres) {
        this.gameGenres = gameGenres;
    }
}
