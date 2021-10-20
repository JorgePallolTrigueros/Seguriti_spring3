package com.clases.security.usuarios.repository;
import com.clases.security.usuarios.dao.entity.GalleryEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.GalleryRepository;
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
public class GalleryRepositoryTest {


    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private GalleryRepository galleryRepository;



    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllGalerys() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        //imprimir la cantidad de usuarios
        log.info("Users: {}", galleryRepository.count());
        //mostrar cada usuario en la lista de findAll
        galleryRepository.findAll().forEach(galleryEntity -> log.info(galleryEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveGaleryAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        GalleryEntity galleryEntity = new GalleryEntity();
        //llenar todos los campos menos el email
        galleryEntity.setUrl("Nuevo");

        //guardar el usuario y devolver (saveAndFlush)
        galleryEntity =  galleryRepository.saveAndFlush(galleryEntity);

        //imprimir la cantidad de usuarios
        log.info("Galeria: {}", galleryRepository.count());

        //buscar el usuario
        Optional<GalleryEntity> galeryResult = galleryRepository.findById(galleryEntity.getId());
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
        Optional<GalleryEntity> galeryOptionalResult = galleryRepository.findAll().stream().findFirst();
        //si no esta presente
        if(!galeryOptionalResult.isPresent()){
            log.info("No hay usuarios en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        GalleryEntity galleryEntity = galeryOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("USER ORIGINAL: {}", galleryEntity);

        //guardo el rol original
        String originalRol = galleryEntity.getUrl();

        //modifico el rol
        galleryEntity.setUrl("MODIFICADO "+AppUtil.currentDate());
        //guardo los cambios
        galleryEntity = galleryRepository.saveAndFlush(galleryEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(galleryEntity.getUrl().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("USER MODIFIED: {}", galleryEntity);


    }

    @Test
    void Test_04_FindAnyGaleryAndDeleteGalery(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Gallery: {}",galleryRepository.count());

        GalleryEntity galleryEntity1 = new GalleryEntity();
        //llenar todos los campos menos el email
        galleryEntity1.setUrl("A borrar");

        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        galleryEntity1 =  galleryRepository.saveAndFlush(galleryEntity1);

        log.info("Foto guardado correctamente");

        log.info("Fotos: {}",galleryRepository.count());

        //buscar un usuario
        Optional<GalleryEntity> galleryResult = galleryRepository.findById(galleryEntity1.getId());



        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(galleryResult.isPresent()){
            //si existe lo imprimimos por consola
            log.info("User To delete: {}",galleryResult.get());
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            Assertions.fail("No hay registro");
        }


        //buscar el usuario y borrarlo
        galleryRepository.deleteById(galleryResult.get().getId());

        //buscar un usuario ya eliminado
        galleryResult = galleryRepository.findById(galleryResult.get().getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(galleryResult.isPresent()){
            //si existe ex por que no se eliminado correctamente
            Assertions.fail("El dato existe cuando deberia haberse eliminado");
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            log.info("El registro ya no existe mas en la base de datos");
        }

        //imprimir la cantidad de usuarios
        log.info("Users: {}",galleryRepository.count());
    }
}
