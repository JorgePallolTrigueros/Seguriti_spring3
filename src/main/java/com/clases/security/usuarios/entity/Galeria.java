package com.clases.security.usuarios.entity;

import com.clases.security.usuarios.dto.SerieDto;
@Entity
@Table(name="galeria")
public class Galeria implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(length =5550, name="imagen")
    private String imagen;
    @Column(length =5550, name="idserie")
    private SerieDto idserie;


}
