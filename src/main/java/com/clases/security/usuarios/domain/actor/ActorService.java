package com.clases.security.usuarios.domain.actor;

import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.domain.shared.dto.ActorDto;
import com.clases.security.usuarios.dao.entity.ActorEntity;
import com.clases.security.usuarios.dao.repository.ActorRepository;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ActorService {


    private ActorDto idactor;

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ActorRepository actorRepository;
    private final ModelMapper mapper;

    public ActorService(ActorRepository actorRepository, ModelMapper mapper) {
        this.actorRepository = actorRepository;
        this.mapper = mapper;
    }


    /**
     * Devuelve la lista de usuarios
     * @return List<ActorDto>
     */
    public List<ActorDto> findAllActors(){
        return actorRepository
                .findAll() //llamar al repositorio que nos devuelve una lista
                .stream() // con stream lo convierto en un flujo que puedo trabajar
                .map( actorEntity -> mapper.map(actorEntity,ActorDto.class) ) //mapeo cada elemento a ActorDto.class
                .collect(Collectors.toList());//recolectar los resultados y devolver una lista
    }

    /**
     * Buscar usuario por id
     * @param id
     * @param model
     * @return
     */
    public String viewActor(Long id, Model model) {
        Optional<ActorEntity> actorEntityOptional = actorRepository.findById(id);

        if(actorEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("actor", mapper.map(actorEntityOptional.get(), ActorDto.class) );
            return "actor-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String deleteActor(Long id, Model model) {
        Optional<ActorEntity> actorEntityOptional = actorRepository.findById(id);

        if(actorEntityOptional.isPresent()){
            //si esta presente
            actorRepository.deleteById(id);
            return "index";
        }else{
            //si no esta presente
            //devolver a una pagina de error no se encuentra
            return "index";
        }
    }

    public String saveActor(Model model, ActorDto actorDto) {

        log.info("actor to save: "+actorDto.toString());

        Optional<ActorEntity> actorEntityOptional = actorRepository.findByActorname(actorDto.getName());

        //si hay un usuario con el username debe lanzar un error
        if(actorEntityOptional.isPresent()){
            model.addAttribute("error","Ya existe el actor con el mismo nombre");
            return "error";
        }



        ActorEntity actorEntity = mapper.map(actorDto,ActorEntity.class);

        actorEntity = actorRepository.saveAndFlush(actorEntity);
        //agregar al model attribute
        model.addAttribute("actor", mapper.map(actorEntity, ActorDto.class) );
        //retornar la vista

        return "actor-list";

    }




    public String editActor(Model model,ActorDto actorDto) {
        Optional<ActorEntity> actorEntityOptional = actorRepository.findById(actorDto.getId());

        if(actorEntityOptional.isPresent()){
            //si esta presente
            ActorEntity  actorEntity = actorEntityOptional.get();
            //mapear de un objeto ya existente los datos de otro
            mapper.map(actorDto,actorEntity);
            //guardar
            actorEntity = actorRepository.saveAndFlush(actorEntity);
            //agregar al model attribute
            model.addAttribute("actor", mapper.map(actorEntity, ActorDto.class) );
            //retornar la vista
            return "actor-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String viewActorEdit(Long id, Model model) {
        Optional<ActorEntity> actorEntityOptional = actorRepository.findById(id);

        if(actorEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("actor", mapper.map(actorEntityOptional.get(), ActorDto.class) );
            return "actor-edit";
        }else{
            //si no esta presente
            return "index";
        }
    }
}


