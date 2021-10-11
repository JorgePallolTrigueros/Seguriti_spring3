package com.clases.security.usuarios.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="gallery")
public class GaleryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;


    @Column(length =6650, name="url")
    private String url;


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

    public GaleryEntity( String url) {

        this.url = url;
    }

    public GaleryEntity() {
    }

    @Override
    public String toString() {
        return "GaleryEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
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
        if (!(object instanceof GaleryEntity)) {
            return false;
        }
        GaleryEntity other = (GaleryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
