package com.clases.security.usuarios.service;

import com.clases.security.usuarios.dto.ActorDto;
import com.clases.security.usuarios.dto.SerieDto;
import com.clases.security.usuarios.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActorService {

    private SerieDto idserie;

    /**
     * Devuelve la lista de Actores
     * @return List<ActorDto>
     */
    public List<ActorDto> findAllUsers(){

        List<ActorDto> actors = new ArrayList<>();

        for(int i=0;i<10;i++){
            actors.add( new ActorDto(new ActorDto((long),"","","","" ));
        }

        return actors;
    }

}
