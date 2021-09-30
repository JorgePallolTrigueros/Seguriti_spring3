package com.clases.security.usuarios.service;

import com.clases.security.usuarios.dto.ActorDto;
import com.clases.security.usuarios.dto.GaleriaDto;
import com.clases.security.usuarios.dto.SerieDto;
import com.clases.security.usuarios.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class GaleriaService {


    private SerieDto idserie;


    public List<GaleriaDto> findAllGalerias(){

        List<GaleriaDto> galerias = new ArrayList<>();

        for(int i=0;i<10;i++){
            galerias.add( new GaleriaDto((long)i,"",idserie));
        }

        return galerias;
    }
}
