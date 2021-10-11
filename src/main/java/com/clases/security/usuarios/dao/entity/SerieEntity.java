package com.clases.security.usuarios.dao.entity;

import java.util.Date;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


import javax.persistence.Id;



@Entity
@Table(name="serie")
public class SerieEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;


    @Column(length =50, name="nombre")
    private String name;
    @Column(length =50, name="active")
    private String active;
    @Column(length =50, name="genero")
    private String genero;
    @Column(length =50, name="estreno")
    private Date estreno;
    @Column(length =50, name="description")
    private String description;
    @Column(length =50, name="imagen")
    private String imagen;




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

    public SerieEntity(String name, String active, String genero, String description) {

        this.name = name;
        this.active = active;
        this.genero = genero;
        this.estreno = estreno;
        this.description = description;
        this.imagen = imagen;
    }

    public SerieEntity() {
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SerieEntity)) {
            return false;
        }
        SerieEntity other = (SerieEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "SerieEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active='" + active + '\'' +
                ", genero='" + genero + '\'' +
                ", estreno=" + estreno +
                ", description='" + description + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
