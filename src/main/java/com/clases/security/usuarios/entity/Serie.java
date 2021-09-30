package com.clases.security.usuarios.entity;

import java.util.Date;

@Entity
@Table(name="serie")
public class Serie implements Serializable{


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    @Column(length =50, name="nombre")
    private String name;
    @Column(length =50, name="asctive")
    private String asctive;
    @Column(length =50, name="genero")
    private String genero;
    @Column(length =50, name="estreno")
    private Date estreno;
    @Column(length =50, name="description")
    private String description;

}
