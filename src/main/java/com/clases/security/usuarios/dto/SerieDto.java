package com.clases.security.usuarios.dto;

import java.util.Date;

public class SerieDto {

    private Long id;
    private String name;
    private String asctive;
    private String genero;
    private Date estreno;
    private String description;


    public SerieDto(long i, String s, String s1, String s2) {
    }

    public SerieDto(Long id, String name, String asctive, String genero, Date estreno, String description) {
        this.id = id;
        this.name = name;
        this.asctive = asctive;
        this.genero = genero;
        this.estreno = estreno;
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

    public String getAsctive() {
        return asctive;
    }

    public void setAsctive(String asctive) {
        this.asctive = asctive;
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
                ", asctive='" + asctive + '\'' +
                ", genero='" + genero + '\'' +
                ", estreno=" + estreno +
                ", description='" + description + '\'' +
                '}';
    }
}
