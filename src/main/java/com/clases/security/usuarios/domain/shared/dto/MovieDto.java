package com.clases.security.usuarios.domain.shared.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDto{

    private Long id;
    private String name;
    private String active;
    private String genre;
    private String description;
    private String image;





    /** relaciones entre tablas **/
    private List<GalleryDto> pictures = new ArrayList<>();

    public MovieDto() {
    }

    public MovieDto(Long id) {
        this.id = id;
    }

    public MovieDto(Long id, String name, String active, String genre,
                    String description, String image, List<GalleryDto> pictures) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.genre = genre;
        this.description = description;
        this.image = image;
        this.pictures = pictures;
    }


    /** getter y setter **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<GalleryDto> getPictures() {
        return pictures;
    }

    public void setPictures(List<GalleryDto> pictures) {
        this.pictures = pictures;
    }


    @Override
    public String toString() {
        return "MovieDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active='" + active + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", pictures=" + pictures +
                '}';
    }
}
