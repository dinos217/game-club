package com.vromo.gameclub.dtos;

import lombok.Builder;

@Builder
public class GenreDto {

    private Long id;
    private String genreName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
