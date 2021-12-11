package com.clases.security.usuarios.domain.shared.dto;

public class GalleryDto {

    private Long id;
    private String url;
    private MovieDto movie;

    public GalleryDto() {
    }

    public GalleryDto(Long id, String url, MovieDto movie) {
        this.id = id;
        this.url = url;
        this.movie = movie;
    }

    public GalleryDto( MovieDto movie) {
        this.movie = movie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "GalleryDto{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", movie=" + movie +
                '}';
    }
}
