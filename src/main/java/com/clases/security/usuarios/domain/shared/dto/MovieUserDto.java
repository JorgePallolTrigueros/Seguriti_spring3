package com.clases.security.usuarios.domain.shared.dto;

import com.clases.security.usuarios.dao.entity.MovieEntity;
import com.clases.security.usuarios.dao.entity.MovieUserEntityPK;
import com.clases.security.usuarios.dao.entity.UserEntity;

import javax.persistence.*;
import java.util.Date;

public class MovieUserDto {

    private UserEntity user;
    private MovieEntity movie;
    private Integer valoration;
    private String critic;
    private Date viewDate;


    public MovieUserDto() {
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
    public String toString() {
        return "MovieUserDto{" +
                "user=" + user +
                ", movie=" + movie +
                ", valoration=" + valoration +
                ", critic='" + critic + '\'' +
                ", viewDate=" + viewDate +
                '}';
    }
}
