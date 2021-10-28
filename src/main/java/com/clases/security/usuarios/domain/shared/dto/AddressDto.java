package com.clases.security.usuarios.domain.shared.dto;

public class AddressDto {

    private Long id;
    private String address;
    private Long idUser;

    public AddressDto() {
    }

    public AddressDto(Long id) {
        this.id = id;
    }

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

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "AddressDto{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", idUser=" + idUser +
                '}';
    }
}
