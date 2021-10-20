package com.clases.security.usuarios.repository;


import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.UserRepository;
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
public class UserRepositoryTest {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private UserRepository userrepository;


    /**
     * Se llama despues de crear la clase
     * Es el primer metodo despues del constructor en ser llamado
     */
    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            userrepository.save(new UserEntity("User"+i,"ADMIN","ACTIVO"));
        }
        initialized = true;
    }

    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllUsers() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        //imprimir la cantidad de usuarios
        log.info("Users: {}",userrepository.count());
        //mostrar cada usuario en la lista de findAll
        userrepository.findAll().forEach(userEntity -> log.info(userEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveUserAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        UserEntity userEntity = new UserEntity();
        //llenar todos los campos menos el email
        userEntity.setName("Nuevo");
        userEntity.setRol("ADMIN");
        userEntity.setCreated(new Date());
        userEntity.setStatus("ACTIVO");
        userEntity.setImagen("imagen");
        userEntity.setEmail("prueba@email.com");

        //guardar el usuario y devolver (saveAndFlush)
        userEntity =  userrepository.saveAndFlush(userEntity);

        //imprimir la cantidad de usuarios
        log.info("Users: {}",userrepository.count());

        //buscar el usuario
        Optional<UserEntity> userResult = userrepository.findById(userEntity.getId());
        if(userResult.isPresent()){
            //se encontro el usuario se imprimira la informacion
            log.info("FOUND: {}",userResult.get());
        }else{
            //si no hay usuario
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

    }

    @Test
    void Test_03_FindAnyUserAndUpdateUser(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        //de toda la lista de usuarios
        //cogere el primero, si existe (estara envuelto en optional debo evaluar si esta presente, es decir si hay)
        Optional<UserEntity> userOptionalResult = userrepository.findAll().stream().findFirst();

        //si no esta presente
        if(!userOptionalResult.isPresent()){
            log.info("No hay usuarios en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        UserEntity userEntity = userOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("USER ORIGINAL: {}",userEntity);

        //guardo el rol original
        String originalRol = userEntity.getRol();

        //modifico el rol
        userEntity.setRol("MODIFICADO "+AppUtil.currentDate());
        //guardo los cambios
        userEntity = userrepository.saveAndFlush(userEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(userEntity.getRol().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("USER MODIFIED: {}",userEntity);


    }

    @Test
    void Test_04_FindAnyUserAndDeleteUser(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}",userrepository.count());

        UserEntity userEntity1 = new UserEntity();
        //llenar todos los campos menos el email
        userEntity1.setName("A borrar");
        userEntity1.setRol("A Borrar");
        userEntity1.setCreated(new Date());
        userEntity1.setStatus("A Borrar");
        userEntity1.setImagen("imagen a Borrar");
        userEntity1.setEmail("prueba@email.com");

        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        userEntity1 =  userrepository.saveAndFlush(userEntity1);

        log.info("Usuario guardado correctamente");

        log.info("Users: {}",userrepository.count());

        //buscar un usuario
        Optional<UserEntity> userResult = userrepository.findById(userEntity1.getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(userResult.isPresent()){
            //si existe lo imprimimos por consola
            log.info("User To delete: {}",userResult.get());
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            Assertions.fail("No hay registro");
        }


        //buscar el usuario y borrarlo
        userrepository.deleteById(userResult.get().getId());

        //buscar un usuario ya eliminado
        userResult = userrepository.findById(userResult.get().getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(userResult.isPresent()){
            //si existe ex por que no se eliminado correctamente
            Assertions.fail("El dato existe cuando deberia haberse eliminado");
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            log.info("El registro ya no existe mas en la base de datos");
        }

        //imprimir la cantidad de usuarios
        log.info("Users: {}",userrepository.count());
    }


    //TODO tarea crear el test de eliminar y comprobar que se ha eliminado
    //TODO para esto hacer uso de los codigos explicado en estos metodos, findById,assert,optional Etc lo que te haga falta


    //TODO despues de realizar lo anterior hacer prueba por cada entidad que se vaya realizando
    //TODO solamente despues de realizar TODOS los test de la entidad pasar a la siguiente

    //TODO no olvidar los metodos en las entidades, hash,toString,etc ademas de renombrar y poner en carpetas dao


}
