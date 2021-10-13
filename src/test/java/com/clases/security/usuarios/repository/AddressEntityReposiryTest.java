package com.clases.security.usuarios.repository;
import com.clases.security.usuarios.dao.entity.AddressEntity;
import com.clases.security.usuarios.dao.repository.DirectionRepository;
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
public class AddressEntityReposiryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private DirectionRepository directionrepository;


    /**
     * Se llama despues de crear la clase
     * Es el primer metodo despues del constructor en ser llamado
     */
    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            directionrepository.save(new AddressEntity("Nuevo"));
        }
        initialized = true;
    }

    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllDireccion() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        //imprimir la cantidad de usuarios
        log.info("Users: {}",directionrepository.count());
        //mostrar cada usuario en la lista de findAll
        directionrepository.findAll().forEach(directionEntity -> log.info(directionEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveDireccionAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        AddressEntity directionEntity = new AddressEntity("");
        //llenar todos los campos menos el email
        directionEntity.setName("Nuevo");


        //guardar el usuario y devolver (saveAndFlush)
        directionEntity =  directionrepository.saveAndFlush(directionEntity);

        //imprimir la cantidad de usuarios
        log.info("Users: {}",directionrepository.count());

        //buscar el usuario
        Optional<AddressEntity> directionResult = directionrepository.findById(directionEntity.getId());
        if(directionResult.isPresent()){
            //se encontro el usuario se imprimira la informacion
            log.info("FOUND: {}",directionResult.get());
        }else{
            //si no hay usuario
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

    }

    @Test
    void Test_03_FindAnyDireccionAndUpdateDireccion(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        //de toda la lista de usuarios
        //cogere el primero, si existe (estara envuelto en optional debo evaluar si esta presente, es decir si hay)
        Optional<AddressEntity> directionOptionalResult = directionrepository.findAll().stream().findFirst();

        //si no esta presente
        if(!directionOptionalResult.isPresent()){
            log.info("No hay DIRECCIONES  en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        AddressEntity directionEntity = directionOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("DIRECCION ORIGINAL: {}",directionEntity);

        //guardo el rol original
        String originalRol = directionEntity.getName();

        //modifico el rol
        directionEntity.setName("MODIFICADO");
        //guardo los cambios
        directionEntity = directionrepository.saveAndFlush(directionEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(directionEntity.getName().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("DIRECCION  MODIFIED: {}",directionEntity);


    }

    @Test
    void Test_04_FindAnyDireccionAndDeleteDireccion(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}",directionrepository.count());

        AddressEntity directionEntity1 = new AddressEntity("User");
        //llenar todos los campos menos el email
        directionEntity1.setName("A borrar");


        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        directionEntity1 =  directionrepository.saveAndFlush(directionEntity1);

        //buscar el usuario y borrarlo
        directionrepository.deleteById(directionEntity1.getId());

        //imprimir la cantidad de usuarios
        log.info("Users: {}",directionrepository.count());
    }







}
