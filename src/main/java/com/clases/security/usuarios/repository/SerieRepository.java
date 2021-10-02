package com.clases.security.usuarios.repository;
import com.clases.security.usuarios.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SerieRepository  extends JpaRepository<Serie, Long>{
}
