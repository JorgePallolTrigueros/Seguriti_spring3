package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.MovieActorEntity;
import com.clases.security.usuarios.dao.entity.MovieActorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActorEntity, MovieActorEntityPK> {

    @Query(value = "SELECT ma FROM MovieActorEntity ma WHERE ma.pk.idMovie = :idMovie")
    List<MovieActorEntity> findAllMovieActorByIdMovie(Long idMovie);

    @Query(value = "SELECT ma FROM MovieActorEntity ma WHERE ma.pk.idActor = :idActor")
    List<MovieActorEntity> findAllMovieActorByIdActor(Long idActor);

}
