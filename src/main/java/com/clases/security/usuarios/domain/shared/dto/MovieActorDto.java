package com.clases.security.usuarios.domain.shared.dto;

import java.util.Date;

public class MovieActorDto {

    //TODO version simplificada de movieACtorEntity y no me hace falta poner el PK, por que ya tengo actor y movie
    private ActorDto actor;
    private MovieDto movie;
    private String character;
    private Date participation;

    public MovieActorDto() {
    }


    public ActorDto getActor() {
        return actor;
    }

    public void setActor(ActorDto actor) {
        this.actor = actor;
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Date getParticipation() {
        return participation;
    }

    public void setParticipation(Date participation) {
        this.participation = participation;
    }

    @Override
    public String toString() {
        return "MovieActorDto{" +
                "actor=" + actor +
                ", movie=" + movie +
                ", character='" + character + '\'' +
                ", participation=" + participation +
                '}';
    }
}
