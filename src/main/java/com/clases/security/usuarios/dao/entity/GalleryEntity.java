package com.clases.security.usuarios.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="gallery")
public class GalleryEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(length =6650, name="url")
    private String url;

    /** relaciones entre tablas **/

    @ManyToOne
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;


    public GalleryEntity() {

    }


    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public MovieEntity getMovie() {
        return movie;
    }

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


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof GalleryEntity)) {
            return false;
        }
        GalleryEntity other = (GalleryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GaleryEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }

}
