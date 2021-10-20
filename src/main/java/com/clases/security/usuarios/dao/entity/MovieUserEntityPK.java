package com.clases.security.usuarios.dao.entity;


import com.sun.istack.NotNull;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieUserEntityPK implements Serializable {


    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "user")//nombre de columna en base de jpa
    private Long idUser;

    @Basic(optional = false)
    @NotNull
    @Column(name = "movie")
    private Long idMovie;


    public MovieUserEntityPK() {
    }

    public MovieUserEntityPK(Long idUser, Long idMovie) {
        this.idUser = idUser;
        this.idMovie = idMovie;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }


    @Override
    public boolean equals(Object object) {



        if (!(object instanceof MovieUserEntityPK)) {
            return false;
        }
        MovieUserEntityPK other = (MovieUserEntityPK) object;

        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }

        if ((this.idMovie == null && other.idMovie != null) || (this.idMovie != null && !this.idMovie.equals(other.idMovie))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        hash += (idMovie != null ? idMovie.hashCode() : 0);
        return hash;
    }
}
