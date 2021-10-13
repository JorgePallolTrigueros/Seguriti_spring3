package com.clases.security.usuarios.dao.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Relaciones muchos a muchos
 */
@Entity
@Table(name="movie_actor")
public class MovieActorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //llave personalizada y compuesta
    @EmbeddedId
    private MovieActorEntityPK pk;

    //relaciones de llave foranea

    @ManyToOne
    @MapsId("idActor")
    @JoinColumn(name = "id_actor")
    private ActorEntity actor;

    @ManyToOne
    @MapsId("idMovie")
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    //campos extras que se desean agregar a la tabla intermedia
    @Column(name="participation")
    private Date participation;

    //constructor

    public MovieActorEntity() {
    }

    public MovieActorEntityPK getPk() {
        return pk;
    }

    public void setPk(MovieActorEntityPK pk) {
        this.pk = pk;
    }

    public ActorEntity getActor() {
        return actor;
    }

    public void setActor(ActorEntity actor) {
        this.actor = actor;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public Date getParticipation() {
        return participation;
    }

    public void setParticipation(Date participation) {
        this.participation = participation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MovieActorEntity)) {
            return false;
        }
        MovieActorEntity other = (MovieActorEntity) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MovieActorEntity{" +
                "pk=" + pk +
                ", actor=" + (actor!=null?actor.getId():"null") +
                ", movie=" + (movie!=null?movie.getId():"null") +
                ", participation=" + participation +
                '}';
    }
}
