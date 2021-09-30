package com.clases.security.usuarios.entity;

import com.clases.security.usuarios.dto.SerieDto;

public class Actor implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(length =50, name="nombre")
    private String name;
    @Column(length =5550, name="imagen")
    private String imagen;
    @Column(length =5550, name="idserie")
    private SerieDto idserie;
    @Column(length =5550, name="descripcion")
    private String description;



}
