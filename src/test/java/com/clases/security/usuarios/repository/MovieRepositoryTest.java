package com.clases.security.usuarios.repository;


import com.clases.security.usuarios.dao.entity.MovieEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.util.AppUtil;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.Date;
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
        movieEntity.setDescription("MODIFICADO SERIE "+AppUtil.currentDate());
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
    void Test_04_FindAnyMovieAndDeleteSerie(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Movie: {}",movieRepository.count());

        MovieEntity movieEntity1 = new MovieEntity();
        //llenar todos los campos menos el email
        movieEntity1.setName("A borrar");
        movieEntity1.setDescription("A Borrar");
        movieEntity1.setReleased(new Date());
        movieEntity1.setGenre("A Borrar");

        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        movieEntity1 =  movieRepository.saveAndFlush(movieEntity1);

        log.info("Peli o Serie guardada correctamente");

        log.info("Pelis o Series guardadas : {}",movieRepository.count());

        //buscar un usuario
        Optional<MovieEntity> movieResult = movieRepository.findById(movieEntity1.getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(movieResult.isPresent()){
            //si existe lo imprimimos por consola
            log.info("Peli o Serie guardada para borrar: {}",movieResult.get());
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            Assertions.fail("No hay registro");
        }


        //buscar el usuario y borrarlo
        movieRepository.deleteById(movieResult.get().getId());

        //buscar un usuario ya eliminado
        movieResult = movieRepository.findById(movieResult.get().getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(movieResult.isPresent()){
            //si existe ex por que no se eliminado correctamente
            Assertions.fail("El dato existe cuando deberia haberse eliminado");
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            log.info("El registro ya no existe mas en la base de datos");
        }

        //imprimir la cantidad de usuarios
        log.info("Users: {}",movieRepository.count());

    }

}
