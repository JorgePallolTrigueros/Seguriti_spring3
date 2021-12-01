package com.clases.security.usuarios.dao.repository;


import com.clases.security.usuarios.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

    @Query(value = "SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findByUsername(String username);

}
