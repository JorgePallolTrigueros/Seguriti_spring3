package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.MovieUserEntity;
import com.clases.security.usuarios.dao.entity.MovieUserEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieUserRepository extends JpaRepository<MovieUserEntity, MovieUserEntityPK> {


    @Query(value = "SELECT m FROM MovieUserEntity m WHERE m.pk.idUser = :idUser")
    List<MovieUserEntity> findAllByUserId(Long idUser);
}
