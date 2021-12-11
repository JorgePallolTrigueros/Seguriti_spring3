package com.clases.security.usuarios.dao.entity;

import java.util.ArrayList;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="movie")
public class MovieEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(length =50, name="name")
    private String name;
    @Column(length =50, name="active")
    private String active;
    @Column(length =50, name="genre")
    private String genre;

    @Column(length =50, name="description")
    private String description;
    @Column(length =50, name="image")
    private String image;


    /** relaciones entre tablas **/

    @JoinColumn(name ="id_movie")
    @OneToMany
    private List<GalleryEntity> pictures = new ArrayList<>();





    //constructor
    public MovieEntity() {

    }

    public MovieEntity(String name, String active, String genre, String description) {

        this.name = name;
        this.active = active;
        this.genre = genre;
        this.description = description;
        this.image = image;
    }









    //getters y setters

    public List<GalleryEntity> getPictures() {
        return pictures;
    }

    public void setPictures(List<GalleryEntity> pictures) {
        this.pictures = pictures;
    }

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








    //metodos generados

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MovieEntity)) {
            return false;
        }
        MovieEntity other = (MovieEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    //al final

    @Override
    public String toString() {
        return "SerieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active='" + active + '\'' +
                ", genero='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", imagen='" + image + '\'' +
                ", pictures=" + pictures.size() +
                '}';
    }
}
