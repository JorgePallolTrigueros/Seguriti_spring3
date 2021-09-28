package com.clases.security.usuarios.dto;

import java.util.Date;

public class UserDto {

    private Long id;
    private String name;
    private String rol;
    private String status;
    private Date created;
    private String email;

    public UserDto() {
        //constructor vacio
    }

    public UserDto(Long id, String name, String rol, String status, Date created, String email) {
        this.id = id;
        this.name = name;
        this.rol = rol;
        this.status = status;
        this.created = created;
        this.email = email;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rol='" + rol + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", email='" + email + '\'' +
                '}';
    }
}
