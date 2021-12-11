package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.ActorEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ActorRepository  extends JpaRepository<ActorEntity, Long> {

    @Query(value = "SELECT a FROM ActorEntity a WHERE a.name = :name")
    Optional<ActorEntity> findByActorname(String name);

}
