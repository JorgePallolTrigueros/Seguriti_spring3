package com.clases.security.usuarios.dto;

import java.util.Date;

public class DirectionDto {

    private Long id;
    private String name;
    private UserDto idUser;

    public DirectionDto(DirectionDto directionDto) {
    }

    public DirectionDto(Long id, String name, UserDto idUser) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
    }

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

    public UserDto getIdUser() {
        return idUser;
    }

    public void setIdUser(UserDto idUser) {
        this.idUser = idUser;
    }


    @Override
    public String toString() {
        return "DirectionDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
