package com.clases.security.usuarios.repository;


import com.clases.security.usuarios.dao.entity.SerieEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.SerieRepository;
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
public class SerieEnityRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private SerieRepository serieRepository;



    /**
     * Se llama despues de crear la clase
     * Es el primer metodo despues del constructor en ser llamado
     */
    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            SerieEntity save = serieRepository.save(new SerieEntity("aa"+i,"ss"+i,"dd"+i,"ff"+i));
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
        log.info("Series: {}",serieRepository.count());
        //mostrar cada usuario en la lista de findAll
        serieRepository.findAll().forEach(SerieEntity -> log.info(SerieEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveSerieAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        SerieEntity serieEntity = new SerieEntity();
        serieEntity.setName("Nombre1");
        serieEntity.setDescription("Descripcion 1");
        serieEntity.setGenero("Genero_1");
        serieEntity.setActive("ace");


        //guardar el usuario y devolver (saveAndFlush)
        serieEntity =  serieRepository.saveAndFlush(serieEntity);

        //imprimir la cantidad de usuarios
        log.info("Series: {}",serieRepository.count());

        //buscar el Series
        Optional<SerieEntity> serieResult = serieRepository.findById(serieEntity.getId());

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
        Optional<SerieEntity> serieOptionalResult = serieRepository.findAll().stream().findFirst();

        //si no esta presente
        if(!serieOptionalResult.isPresent()){
            log.info("No hay series en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        SerieEntity serieEntity = serieOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("USER ORIGINAL: {}",serieEntity);

        //guardo el rol original
        String originalRol = serieEntity.getDescription();

        //modifico el rol
        serieEntity.setDescription("MODIFICADO SERIE");
        //guardo los cambios
        serieEntity = serieRepository.saveAndFlush(serieEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(serieEntity.getDescription().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("Serie MODIFIED: {}",serieEntity);


    }

    @Test
    void Test_04_FindAnySerieAndDeleteSerie(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}",serieRepository.count());

        SerieEntity serieEntity = new SerieEntity();
        serieEntity.setName("Nombre1");
        serieEntity.setDescription("Descripcion 1");
        serieEntity.setGenero("Genero_1");
        serieEntity.setActive("ace");


        //guardar el usuario y devolver (saveAndFlush)
        serieEntity =  serieRepository.saveAndFlush(serieEntity);

        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        serieEntity =  serieRepository.saveAndFlush(serieEntity);

        //buscar el usuario y borrarlo
        serieRepository.deleteById(serieEntity.getId());

        //imprimir la cantidad de usuarios
        log.info("Users: {}",serieRepository.count());

    }

}
