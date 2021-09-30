package com.clases.security.usuarios.entity;

import com.clases.security.usuarios.dto.SerieDto;

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
