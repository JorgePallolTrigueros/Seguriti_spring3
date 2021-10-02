package com.clases.security.usuarios.entity;

import com.clases.security.usuarios.dto.SerieDto;
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

@Entity
@Table(name="actor")
public class Actor implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(length =50, name="nombre")
    private String name;
    @Column(length =5550, name="imagen")
    private String imagen;
    @Column(length =5550, name="idserie")
    private Serie idserie;
    @Column(length =5550, name="descripcion")
    private String description;

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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Serie getIdserie() {
        return idserie;
    }

    public void setIdserie(Serie idserie) {
        this.idserie = idserie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Actor(Long id, String name, String imagen, Serie idserie, String description) {
        this.id = id;
        this.name = name;
        this.imagen = imagen;
        this.idserie = idserie;
        this.description = description;
    }

    public Actor() {
    }
}
