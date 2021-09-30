package com.clases.security.usuarios.entity;

import com.clases.security.usuarios.dto.UserDto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;


import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
@Table(name="direction")
public class Direction implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(length =50, name="nombre")
    private String name;
    @Column(length =50, name="idUser")
    private UserDto idUser;



}
