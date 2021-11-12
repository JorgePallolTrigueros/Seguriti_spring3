package com.clases.security.usuarios.repository;


import com.clases.security.usuarios.dao.entity.GalleryEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.GalleryRepository;
import com.clases.security.usuarios.dao.repository.MovieActorRepository;
import com.clases.security.usuarios.util.AppUtil;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)//ordene la ejecucion de los test
public class MovieActorRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieActorRepository repository;


    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllMovieActorByIdMovie() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        long idMovie = 2;
        //mostrar cada usuario en la lista de findAll
        repository.findAllMovieActorByIdMovie(idMovie) .forEach(movieActor -> log.info(movieActor.toString()));
        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }


}
