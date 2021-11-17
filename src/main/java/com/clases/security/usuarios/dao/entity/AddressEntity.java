package com.clases.security.usuarios.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address")
public class AddressEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(length =250, name="content")
    private String address;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    /** constructor */

    public AddressEntity() {
        //constructor vacio
    }

    public AddressEntity(Long id) {
        this.id = id;
    }

    /** getter Y setters **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AddressEntity(String address) {
        this.address = address;
    }




    /** metodos generados **/
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AddressEntity)) {
            return false;
        }
        AddressEntity other = (AddressEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
