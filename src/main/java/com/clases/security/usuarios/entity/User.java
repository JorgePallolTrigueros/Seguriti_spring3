package com.clases.security.usuarios.entity;


import java.util.Date;

@Entity
@Table(name="user")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;

    @Column(length =50, name="nombre")
    private String name;

    @Column(length =50, name="rol")
    private String rol;

    @Column(length =50, name="status")
    private String status;

    @Column(length =50, name="created")
    private Date created;

    @Column(length =50, name="email")
    private String email;

}
