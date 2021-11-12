package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.GalleryEntity;
import com.clases.security.usuarios.dao.entity.MovieActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {


    @Query(value = "SELECT g FROM GalleryEntity g WHERE g.id_movie = :idMovie")
    List<GalleryEntity> findAllGalleryByIdMovie(Long idMovie);
}
