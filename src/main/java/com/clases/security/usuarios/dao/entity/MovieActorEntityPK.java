package com.clases.security.usuarios.dao.entity;


import com.sun.istack.NotNull;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Generar llave compuesta que nos unira:
 * - ID de actor
 * - ID de Movie
 * Para la tabla de MoveActor
 * De esta manera nos aseguramos que la llave de la tabla intermedia siempre sera unica
 * en la relacion de actor y movie
 *
 *
 *
 */
@Embeddable
public class MovieActorEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @NotNull
    @Column(name = "actor")
    private Long idActor;

    @Basic(optional = false)
    @NotNull
    @Column(name = "movie")
    private Long idMovie;

    //constructor
    public MovieActorEntityPK() {
    }

    public MovieActorEntityPK(Long idActor, Long idMovie) {
        this.idActor = idActor;
        this.idMovie = idMovie;
    }

    //getters y setters

    public Long getIdActor() {
        return idActor;
    }

    public void setIdActor(Long idActor) {
        this.idActor = idActor;
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    //metodos generados

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActor != null ? idActor.hashCode() : 0);
        hash += (idMovie != null ? idMovie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof MovieActorEntityPK)) {
            return false;
        }
        MovieActorEntityPK other = (MovieActorEntityPK) object;
        //evaluar un solo campo por if
        if ((this.idActor == null && other.idActor != null) || (this.idActor != null && !this.idActor.equals(other.idActor))) {
            return false;
        }

        if ((this.idMovie == null && other.idMovie != null) || (this.idMovie != null && !this.idMovie.equals(other.idMovie))) {
            return false;
        }
        return true;
    }

    //to string


    @Override
    public String toString() {
        return "MovieActorEntityPK{" +
                "idActor=" + idActor +
                ", idMovie=" + idMovie +
                '}';
    }
}
