package com.clases.security.usuarios.dao.entity;


import java.util.Date;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="user")
public class UserEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;


    @Column(name="enabled")
    private Boolean enabled;

    @Column(length =50, name="username",unique = true)
    private String username;

    @Column(length =50, name="name")
    private String name;

    @Column(length =50, name="rol")
    private String rol;

    @Column(length =50, name="status")
    private String status;

    @Column(length =50, name="created")
    private Date created;

    @Column(length =50, name="email")
    private String email;

    @Column(length =5550, name="imagen")
    private String imagen;

    @Column(length =50, name="password")
    private String password;

    /** relaciones entre tablas **/


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AddressEntity address;


    public UserEntity() {
    }

    public UserEntity(String name, String rol, String status) {
        this.name = name;
        this.rol = rol;
        this.status = status;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
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



    /** metodos de comparacion de la entidad**/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    /** metodo de impresion de datos*/
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rol='" + rol + '\'' +
                ", status='" + status + '\'' +
                ", created=" + created +
                ", email='" + email + '\'' +
                ", imagen='" + imagen + '\'' +
                ", adresses=" + (address!=null ? address.getId() : "null") + '\'' +
                '}';
    }
}
