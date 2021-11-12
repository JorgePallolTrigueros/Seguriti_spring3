package com.clases.security.usuarios.dao.repository;

import com.clases.security.usuarios.dao.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    @Query(value = "SELECT ad FROM AddressEntity ad WHERE ad.idUser = :idUser")
    List<AddressEntity> findAllAddressByIdUser(Long idUser);

}

