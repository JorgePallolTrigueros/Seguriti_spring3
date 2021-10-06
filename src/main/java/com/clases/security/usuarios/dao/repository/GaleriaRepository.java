package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.Galeria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//@Repository
public interface GaleriaRepository /*extends JpaRepository<Galeria, Long>*/ {

    //por el momento no se va a utilizar
    @Query("SELECT g FROM Galeria g WHERE g.historia.id = ?1 ")
    List<Galeria> galeriasInHistoria(Long idHistoria);

    @Query("SELECT CASE WHEN (COUNT(g) > 0) THEN true ELSE false END FROM Galeria g WHERE g.historia.id = ?1 AND g.hash = ?2 ")
    boolean imageExistInHistoriaWithHash(Long idHistoria,String hash);



}
