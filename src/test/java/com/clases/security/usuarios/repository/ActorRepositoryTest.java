package com.clases.security.usuarios.repository;

import com.clases.security.usuarios.dao.entity.ActorEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.ActorRepository;
import com.clases.security.usuarios.util.AppUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Optional;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)//ordene la ejecucion de los test
public class ActorRepositoryTest {



    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = false;

    @Autowired
    private ActorRepository actorRepository;


    /**
     * Se llama despues de crear la clase
     * Es el primer metodo despues del constructor en ser llamado
     */
    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            actorRepository.save(new ActorEntity("Nombre"+i,"imagen"+i,"Biografia"+i,"yy"+i));
        }
        initialized = true;
    }

    /**
     * Metodo que realiza la consulta de los usuarios registrados en la base de datos
     * Si resulta exitoso completa la ejecucion sin problemas
     */
    @Test
    void Test_01_FindAllActors() {
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
        //imprimir la cantidad de usuarios
        log.info("Actors: {}",actorRepository.count());
        //mostrar cada usuario en la lista de findAll
        actorRepository.findAll().forEach(actorEntity -> log.info(actorEntity.toString()));

        //si llego a este punto es por que ejecuto exitosamente el test
        Assertions.assertTrue(true);
    }

    @Test
    void Test_02_SaveActorAndFindById(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        ActorEntity actorEntity = new ActorEntity();
        //llenar todos los campos menos el email
        actorEntity.setName("Nuevo");
        actorEntity.setImage("imagen");
        actorEntity.setBiography("biografia");

        //guardar el usuario y devolver (saveAndFlush)
        actorEntity =  actorRepository.saveAndFlush(actorEntity);

        //imprimir la cantidad de usuarios
        log.info("Actors: {}",actorRepository.count());

        //buscar el usuario
        Optional<ActorEntity> actorResult = actorRepository.findById(actorEntity.getId());
        if(actorResult.isPresent()){
            //se encontro el usuario se imprimira la informacion
            log.info("FOUND: {}",actorResult.get());
        }else{
            //si no hay usuario
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

    }

    @Test
    void Test_03_FindAnyActorAndUpdateActor(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        //de toda la lista de usuarios
        //cogere el primero, si existe (estara envuelto en optional debo evaluar si esta presente, es decir si hay)
        Optional<ActorEntity> actorOptionalResult = actorRepository.findAll().stream().findFirst();

        //si no esta presente
        if(!actorOptionalResult.isPresent()){
            log.info("No hay actores en la lista");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si paso el punto anterior es por que si hay usuario en la lista y he cogido uno
        ActorEntity actorEntity = actorOptionalResult.get();

        //imprimo el usuario sin modificar
        log.info("ACTORS ORIGINAL: {}",actorEntity);

        //guardo el rol original
        String originalRol = actorEntity.getBiography();

        //modifico el rol
        actorEntity.setBiography("MODIFICADO");
        //guardo los cambios
        actorEntity = actorRepository.saveAndFlush(actorEntity);

        //si el rol que tengo es igual al original
        //es por que no se ha modificado
        if(actorEntity.getBiography().equals(originalRol)){
            log.info("No se pudo modificar el campo");
            Assertions.fail();//detiene la ejecucion, lanza una excepcion
        }

        //si llega a este punto
        log.info("Si se ha modificado");
        log.info("ACTORS MODIFIED: {}",actorEntity);


    }

    @Test
    void Test_04_FindAnyActorAndDeleteActor(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());

        log.info("Users: {}",actorRepository.count());

        ActorEntity actorEntity1 = new ActorEntity();
        //llenar todos los campos menos el email
        actorEntity1.setName("A borrar");
        actorEntity1.setImage("A Borrar");
        actorEntity1.setBiography("A Borrar");

        //guardar el usuario y devolver (saveAndFlush) para borrarlo
        actorEntity1 =  actorRepository.saveAndFlush(actorEntity1);

        log.info("Usuario guardado correctamente");

        log.info("Users: {}",actorRepository.count());

        //buscar un usuario
        Optional<ActorEntity> actorResult = actorRepository.findById(actorEntity1.getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(actorResult.isPresent()){
            //si existe lo imprimimos por consola
            log.info("Actor To delete: {}",actorResult.get());
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            Assertions.fail("No hay registro");
        }


        //buscar el usuario y borrarlo
        actorRepository.deleteById(actorResult.get().getId());

        //buscar un usuario ya eliminado
        actorResult = actorRepository.findById(actorResult.get().getId());

        //preguntar si el resultado de buscar el usuario con id especificado existe
        if(actorResult.isPresent()){
            //si existe ex por que no se eliminado correctamente
            Assertions.fail("El dato existe cuando deberia haberse eliminado");
        }else{
            //si no existe es por que no se ha guardado correctamente y detenemos la prueba
            log.info("El registro ya no existe mas en la base de datos");
        }

        //imprimir la cantidad de usuarios
        log.info("Users: {}",actorRepository.count());
    }

    @Test
    void Test_05_findByIdActor(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());


        ActorEntity actorEntity2 = new ActorEntity();
        actorEntity2.setName("A Find");
        actorEntity2.setImage("A Find");
        actorEntity2.setBiography("A Find");


        actorEntity2 =  actorRepository.saveAndFlush(actorEntity2);


    }

    @Test
    void Test_06_assertActor(){
        //imprimir el nombre del metodo y la clase
        log.info(AppUtil.getMethodWithClass());
    }


}
