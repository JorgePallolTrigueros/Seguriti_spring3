package com.clases.security.usuarios.repository;
import com.clases.security.usuarios.dao.entity.AddressEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.AddressRepository;
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
public class AddressReposiryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private AddressRepository addressRepository;

    /**
     * Se llama despues de crear la clase
     * Es el primer metodo despues del constructor en ser llamado
     */
    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            addressRepository.save(new AddressEntity("Nuevo"));
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
        log.info("Users: {}",addressRepository.count());
        //mostrar cada usuario en la lista de findAll
        addressRepository.findAll().forEach(directionEntity -> log.info(directionEntity.toString()));

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
        directionEntity =  addressRepository.saveAndFlush(directionEntity);

        //imprimir la cantidad de usuarios
        log.info("Direcciones: {}",addressRepository.count());

        //buscar el usuario
        Optional<AddressEntity> directionResult = addressRepository.findById(directionEntity.getId());
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
        Optional<AddressEntity> directionOptionalResult = addressRepository.findAll().stream().findFirst();

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
        directionEntity.setName("MODIFICADO "+AppUtil.currentDate());
        //guardo los cambios
        directionEntity = addressRepository.saveAndFlush(directionEntity);

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
    void Test_04_FindAnyAddressAndDeleteDireccion(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}",addressRepository.count());

        AddressEntity addressEntity1 = new AddressEntity();
        //llenar todos los campos menos el email
        addressEntity1.setName("A borrar");


        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        addressEntity1 = addressRepository.saveAndFlush(addressEntity1);

        log.info("Usuario guardado correctamente");

        log.info("Users: {}",addressRepository.count());

        //buscar un usuario
        Optional<AddressEntity> addressResult = addressRepository.findById(addressEntity1.getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(addressResult.isPresent()){
            //si existe lo imprimimos por consola
            log.info("User To delete: {}",addressResult.get());
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            Assertions.fail("No hay registro");
        }


        //buscar el usuario y borrarlo
        addressRepository.deleteById(addressResult.get().getId());

        //buscar un usuario ya eliminado
        addressResult = addressRepository.findById(addressResult.get().getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(addressResult.isPresent()){
            //si existe ex por que no se eliminado correctamente
            Assertions.fail("El dato existe cuando deberia haberse eliminado");
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            log.info("El registro ya no existe mas en la base de datos");
        }

        //imprimir la cantidad de usuarios
        log.info("Users: {}",addressRepository.count());
    }







}
