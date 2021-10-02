package com.clases.security.usuarios.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity
@Table(name="galeria")
public class Galeria implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_serie")
    private Serie serie;

    @Column(length =250, name="hash")
    private String hash;

    @Column(length =250, name="ruta")
    private String ruta;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imagen;


    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }


    /**
     * Asociando histora con galeria
     * @param id
     */
    public void setSerieWithId(Long id){
        this.serie = new Serie(id);
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Galeria(String url, Serie serie,String imagen) {
        super();
        this.imagen = imagen;
        this.serie = serie;
    }

    public Galeria() {
        super();
    }




}
