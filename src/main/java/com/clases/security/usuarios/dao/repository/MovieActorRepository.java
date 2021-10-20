package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.MovieActorEntity;
import com.clases.security.usuarios.dao.entity.MovieActorEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieActorRepository extends JpaRepository<MovieActorEntity, MovieActorEntityPK> {

}
