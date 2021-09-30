package com.clases.security.usuarios.dto;

import java.util.Date;

public class SerieDto {

    private Long id;
    private String name;
    private String active;
    private String genero;
    private Date estreno;
    private String description;
    private String imagen;


    public SerieDto(long i, String s, String s1, String s2) {
    }

    public SerieDto(Long id, String name, String active, String genero, Date estreno, String description, String imagen) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.genero = genero;
        this.estreno = estreno;
        this.imagen = imagen;
        this.description = description;
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

    public void setAsctive(String active) {
        this.active = active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getEstreno() {
        return estreno;
    }

    public void setEstreno(Date estreno) {
        this.estreno = estreno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SerieDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagen='" + imagen + '\'' +
                ", active='" + active + '\'' +
                ", genero='" + genero + '\'' +
                ", estreno=" + estreno +
                ", description='" + description + '\'' +
                '}';
    }
}
