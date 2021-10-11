package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.ActorEntity;
import com.clases.security.usuarios.dao.entity.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository  extends JpaRepository<DireccionEntity, Long> {
}

