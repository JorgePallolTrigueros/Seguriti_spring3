package com.clases.security.usuarios.dao.repository;


import com.clases.security.usuarios.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
}
