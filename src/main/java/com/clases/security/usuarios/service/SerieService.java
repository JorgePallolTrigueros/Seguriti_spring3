package com.clases.security.usuarios.service;

import com.clases.security.usuarios.dto.DirectionDto;
import com.clases.security.usuarios.dto.SerieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


@Service
public class SerieService {



    /**
     * Devuelve la lista de Serie
     * @return List<SerieDto>
     */


    public List<SerieDto> findAllUsers(){

        List<SerieDto> series = new ArrayList<>();

        for(int i=0;i<10;i++){

            series.add( new SerieDto(new SerieDto((long)i,"","","","",""));
        }

        return directions;
    }
}
