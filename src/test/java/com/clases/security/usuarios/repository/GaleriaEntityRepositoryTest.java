package com.clases.security.usuarios.repository;
import com.clases.security.usuarios.dao.entity.GaleryEntity;
import com.clases.security.usuarios.dao.repository.GaleryRepository;
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
public class GaleriaEntityRepositoryTest {


    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private GaleryRepository galeryRepository;



    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllGalerys() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        //imprimir la cantidad de usuarios
        log.info("Users: {}",galeryRepository.count());
        //mostrar cada usuario en la lista de findAll
        galeryRepository.findAll().forEach(galeryEntity -> log.info(galeryEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveGaleryAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        GaleryEntity galeryEntity = new GaleryEntity();
        //llenar todos los campos menos el email
        galeryEntity.setUrl("Nuevo");

        //guardar el usuario y devolver (saveAndFlush)
        galeryEntity =  galeryRepository.saveAndFlush(galeryEntity);

        //imprimir la cantidad de usuarios
        log.info("Galeria: {}",galeryRepository.count());

        //buscar el usuario
        Optional<GaleryEntity> galeryResult = galeryRepository.findById(galeryEntity.getId());
        if(galeryResult.isPresent()){
            //se encontro el usuario se imprimira la informacion
            log.info("FOUND: {}",galeryResult.get());
        }else{
            //si no hay usuario
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

    }

    @Test
    void Test_03_FindAnyGaleryAndUpdateGalery(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        //de toda la lista de usuarios
        //cogere el primero, si existe (estara envuelto en optional debo evaluar si esta presente, es decir si hay)
        Optional<GaleryEntity> galeryOptionalResult = galeryRepository.findAll().stream().findFirst();
        //si no esta presente
        if(!galeryOptionalResult.isPresent()){
            log.info("No hay usuarios en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        GaleryEntity galeryEntity = galeryOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("USER ORIGINAL: {}",galeryEntity);

        //guardo el rol original
        String originalRol = galeryEntity.getUrl();

        //modifico el rol
        galeryEntity.setUrl("MODIFICADO");
        //guardo los cambios
        galeryEntity = galeryRepository.saveAndFlush(galeryEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(galeryEntity.getUrl().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("USER MODIFIED: {}",galeryEntity);


    }

    @Test
    void Test_04_FindAnyGaleryAndDeleteGalery(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}",galeryRepository.count());

        GaleryEntity galeryEntity = new GaleryEntity();
        //llenar todos los campos menos el email
        galeryEntity.setUrl("A borrar");


        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        galeryEntity =  galeryRepository.saveAndFlush(galeryEntity);

        //buscar el usuario y borrarlo
        galeryRepository.deleteById(galeryEntity.getId());

        //imprimir la cantidad de usuarios
        log.info("Users: {}",galeryRepository.count());
    }
}
