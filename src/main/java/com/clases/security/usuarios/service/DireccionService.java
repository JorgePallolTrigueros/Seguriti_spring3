package com.clases.security.usuarios.service;

import com.clases.security.usuarios.dto.ActorDto;
import com.clases.security.usuarios.dto.DirectionDto;
import com.clases.security.usuarios.dto.SerieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DireccionService {

    private DirectionDto iduser;

    /**
     * Devuelve la lista de Direccion
     * @return List<DireccionDto>
     */


    public List<DirectionDto> findAllUsers(){

        List<DirectionDto> directions = new ArrayList<>();

        for(int i=0;i<10;i++){
            directions.add( new DirectionDto(new DirectionDto((long)i,"user"+i,iduser ));
        }

        return directions;
    }
}
