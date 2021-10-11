package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.DireccionEntity;
import com.clases.security.usuarios.dao.entity.GaleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GaleryRepository  extends JpaRepository<GaleryEntity, Long> {
}
