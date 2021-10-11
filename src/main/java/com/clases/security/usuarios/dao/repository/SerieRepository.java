package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.SerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface SerieRepository  extends JpaRepository<SerieEntity, Long> {
}
