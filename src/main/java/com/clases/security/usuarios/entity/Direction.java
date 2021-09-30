package com.clases.security.usuarios.entity;

import com.clases.security.usuarios.dto.UserDto;

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
