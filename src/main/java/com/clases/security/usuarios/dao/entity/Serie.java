package com.clases.security.usuarios.dao.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


import javax.persistence.Id;
import javax.persistence.OneToMany;



//@Entity
//@Table(name="serie")
public class Serie implements Serializable{


    private static final long serialVersionUID = 1L;
    //@Id
    //@GeneratedValue
    private Long id;
    //@Column(length =50, name="nombre")
    private String name;
    //@Column(length =50, name="active")
    private String active;
    //@Column(length =50, name="genero")
    private String genero;
    //@Column(length =50, name="estreno")
    private Date estreno;
    //@Column(length =50, name="description")
    private String description;
    //@Column(length =50, name="imagen")
    private String imagen;

    public Serie(Long idSerie) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Serie() {
    }

    public Serie(Long id, String name, String active, String genero, Date estreno, String description, String imagen) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.genero = genero;
        this.estreno = estreno;
        this.description = description;
        this.imagen = imagen;
    }
}
