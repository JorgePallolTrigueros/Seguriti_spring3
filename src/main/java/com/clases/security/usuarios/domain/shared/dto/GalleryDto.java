package com.clases.security.usuarios.domain.shared.dto;

public class GalleryDto {
    private Long id;
    private String imagen;
    private MovieDto idmovie;

    public GalleryDto(Long id, String imagen, MovieDto idmovie) {
        this.id = id;
        this.imagen = imagen;
        this.idmovie = idmovie;
    }

    public GalleryDto() {
    }

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

    public MovieDto getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(MovieDto idmovie) {
        this.idmovie = idmovie;
    }

    @Override
    public String toString() {
        return "GaleriaDto{" +
                "id=" + id +
                ", imagen='" + imagen + '\'' +
                ", idmovie=" + idmovie +
                '}';
    }
}
