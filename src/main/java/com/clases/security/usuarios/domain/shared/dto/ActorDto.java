package com.clases.security.usuarios.domain.shared.dto;

public class ActorDto {

    //TODO actualizar de los nuevos campos de las entidades a esta
    //TODO las entidades
    private Long id;
    private String name;
    private String imagen;
    private SerieDto idserie;
    private String description;

    public ActorDto(Long id, String name, String imagen, SerieDto idserie, String description) {
        this.id = id;
        this.name = name;
        this.imagen = imagen;
        this.idserie = idserie;
        this.description = description;
    }

    public ActorDto(GaleriaDto galeriaDto) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ActorDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idserie=" + idserie +
                ", description='" + description + '\'' +
                '}';
    }
}
