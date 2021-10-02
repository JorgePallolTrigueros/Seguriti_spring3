package com.clases.security.usuarios.repository;
import com.clases.security.usuarios.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ActorRepository  extends JpaRepository<Actor, Long>{
}
