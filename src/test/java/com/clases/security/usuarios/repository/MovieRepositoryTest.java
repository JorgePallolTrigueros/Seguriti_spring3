package com.clases.security.usuarios.repository;


import com.clases.security.usuarios.dao.entity.MovieEntity;
import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.util.AppUtil;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)//ordene la ejecucion de los test
public class MovieRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private MovieRepository movieRepository;



    /**
     * Se llama despues de crear la clase
     * Es el primer metodo despues del constructor en ser llamado
     */
    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            MovieEntity save = movieRepository.save(new MovieEntity("aa"+i,"ss"+i,"dd"+i,"ff"+i));
        }
        initialized = true;
    }
    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllSeries() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        //imprimir la cantidad de usuarios
        log.info("Series: {}", movieRepository.count());
        //mostrar cada usuario en la lista de findAll
        movieRepository.findAll().forEach(MovieEntity -> log.info(MovieEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveSerieAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName("Nombre1");
        movieEntity.setDescription("Descripcion 1");
        movieEntity.setGenre("Genero_1");
        movieEntity.setActive("ace");


        //guardar el usuario y devolver (saveAndFlush)
        movieEntity =  movieRepository.saveAndFlush(movieEntity);

        //imprimir la cantidad de usuarios
        log.info("Series: {}", movieRepository.count());

        //buscar el Series
        Optional<MovieEntity> serieResult = movieRepository.findById(movieEntity.getId());

        if(serieResult.isPresent()){
            //se encontro el usuario se imprimira la informacion
            log.info("FOUND: {}",serieResult.get());
        }else{
            //si no hay usuario
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

    }

    @Test
    void Test_03_FindAnySerieAndUpdateSerie(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        //de toda la lista de usuarios
        //cogere el primero, si existe (estara envuelto en optional debo evaluar si esta presente, es decir si hay)
        Optional<MovieEntity> serieOptionalResult = movieRepository.findAll().stream().findFirst();

        //si no esta presente
        if(!serieOptionalResult.isPresent()){
            log.info("No hay series en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        MovieEntity movieEntity = serieOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("USER ORIGINAL: {}", movieEntity);

        //guardo el rol original
        String originalRol = movieEntity.getDescription();

        //modifico el rol
        movieEntity.setDescription("MODIFICADO SERIE");
        //guardo los cambios
        movieEntity = movieRepository.saveAndFlush(movieEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(movieEntity.getDescription().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("Serie MODIFIED: {}", movieEntity);


    }

    @Test
    void Test_04_FindAnySerieAndDeleteSerie(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}", movieRepository.count());

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName("Nombre1");
        movieEntity.setDescription("Descripcion 1");
        movieEntity.setGenre("Genero_1");
        movieEntity.setActive("ace");


        //guardar el usuario y devolver (saveAndFlush)
        movieEntity =  movieRepository.saveAndFlush(movieEntity);

        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        movieEntity =  movieRepository.saveAndFlush(movieEntity);

        //buscar el usuario y borrarlo
        movieRepository.deleteById(movieEntity.getId());

        //imprimir la cantidad de usuarios
        log.info("Users: {}", movieRepository.count());

    }

}
