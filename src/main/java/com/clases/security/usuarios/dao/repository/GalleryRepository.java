package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
}
