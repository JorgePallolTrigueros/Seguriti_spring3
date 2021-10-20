package com.clases.security.usuarios.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="movie_user")
public class MovieUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //llave personalizada y compuesta
    @EmbeddedId
    private MovieUserEntityPK pk;

    //relaciones de llave foranea

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @ManyToOne
    @MapsId("idMovie")
    @JoinColumn(name = "id_movie")
    private MovieEntity movie;

    //campos extras que se desean agregar a la tabla intermedia
    @Column(name="valoration")
    private Integer valoration;

    @Column(name="critic")
    private String critic;

    //campos extras que se desean agregar a la tabla intermedia
    @Column(name="view_date")
    private Date viewDate;


    /** constructores **/

    public MovieUserEntity() {
    }

    public MovieUserEntity(MovieUserEntityPK pk, UserEntity user, MovieEntity movie, Integer valoration, String critic) {
        this.pk = pk;
        this.user = user;
        this.movie = movie;
        this.valoration = valoration;
        this.critic = critic;
    }


    /** getter y setter */

    public MovieUserEntityPK getPk() {
        return pk;
    }

    public void setPk(MovieUserEntityPK pk) {
        this.pk = pk;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    public Integer getValoration() {
        return valoration;
    }

    public void setValoration(Integer valoration) {
        this.valoration = valoration;
    }

    public String getCritic() {
        return critic;
    }

    public void setCritic(String critic) {
        this.critic = critic;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public void setViewDate(Date viewDate) {
        this.viewDate = viewDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pk != null ? pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MovieUserEntity)) {
            return false;
        }
        MovieUserEntity other = (MovieUserEntity) object;
        if ((this.pk == null && other.pk != null) || (this.pk != null && !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MovieUserEntity{" +
                "pk=" + pk +
                ", user=" + user +
                ", movie=" + movie +
                ", valoration=" + valoration +
                ", viewDate=" + viewDate +
                ", critic='" + critic + '\'' +
                '}';
    }
}
