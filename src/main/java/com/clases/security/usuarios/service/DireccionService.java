package com.clases.security.usuarios.service;

import com.clases.security.usuarios.domain.shared.dto.DirectionDto;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class DireccionService {

    private UserDto iduser;

    /**
     * Devuelve la lista de Direccion
     * @return List<DireccionDto>
     */


    public List<DirectionDto> findAllDirections(){

        List<DirectionDto> directions = new ArrayList<>();

        for(int i=0;i<10;i++){
            directions.add( new DirectionDto((long)i,"",iduser));
        }

        return directions;
    }
}
