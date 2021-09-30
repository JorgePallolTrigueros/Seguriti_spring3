package com.clases.security.usuarios.service;

import com.clases.security.usuarios.dto.ActorDto;
import com.clases.security.usuarios.dto.GaleriaDto;
import com.clases.security.usuarios.dto.SerieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GaleriaService {


    private SerieDto idserie;

    /**
     * Devuelve la lista de Actores
     * @return List<ActorDto>
     */
    public List<GaleriaDto> findAllUsers(){

        List<GaleriaDto> galerias = new ArrayList<>();

        for(int i=0;i<10;i++){
            galerias.add( new ActorDto(new GaleriaDto((long)i,"","") );
        }

        return galerias;
    }
}
