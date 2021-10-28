package com.clases.security.usuarios.domain.shared.dto;

import java.util.Date;

public class UserDto {

    private Long id;
    private String name;
    private String rol;
    private String status;
    private Date created;
    private String email;
    private String imagen;



    public UserDto() {
        //constructor vacio
    }

    public UserDto(Long id, String name,  String imagen, String rol, String status, Date created, String email) {
        this.id = id;
        this.name = name;
        this.rol = rol;
        this.status = status;
        this.created = created;
        this.email = email;
        this.imagen = imagen;
    }

    public UserDto(long i, String s, String admin, String activo, Date date, String s1) {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rol='" + rol + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", imagen=" + imagen +
                ", email='" + email + '\'' +
                '}';
    }
}
