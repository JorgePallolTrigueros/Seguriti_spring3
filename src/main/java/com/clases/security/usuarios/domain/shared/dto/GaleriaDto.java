package com.clases.security.usuarios.domain.shared.dto;

public class GaleriaDto {
    private Long id;
    private String imagen;
    private SerieDto idserie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public SerieDto getIdserie() {
        return idserie;
    }

    public void setIdserie(SerieDto idserie) {
        this.idserie = idserie;
    }

    public GaleriaDto(Long id, String imagen, SerieDto idserie) {
        this.id = id;
        this.imagen = imagen;
        this.idserie = idserie;
    }

    public GaleriaDto() {
    }

    @Override
    public String toString() {
        return "GaleriaDto{" +
                "id=" + id +
                ", imagen='" + imagen + '\'' +
                ", idserie=" + idserie +
                '}';
    }
}
