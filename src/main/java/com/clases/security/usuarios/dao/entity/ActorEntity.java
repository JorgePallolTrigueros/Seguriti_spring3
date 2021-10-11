package com.clases.security.usuarios.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="actor")
public class ActorEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;


    @Column(length =50, name="nombre")
    private String name;

    @Column(length =5660, name="imagen")
    private String imagen;

    @Column(length =5660, name="biografia")
    private String biografia;

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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }


    public ActorEntity(String name, String imagen, String biografia, String s) {

        this.name = name;
        this.imagen = imagen;
        this.biografia = biografia;
    }


    public ActorEntity() {
    }


    @Override
    public String toString() {
        return "ActorEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagen='" + imagen + '\'' +
                ", biografia='" + biografia + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ActorEntity)) {
            return false;
        }
        ActorEntity other = (ActorEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


}
