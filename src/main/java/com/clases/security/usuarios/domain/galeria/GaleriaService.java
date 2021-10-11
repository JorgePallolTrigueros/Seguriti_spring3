package com.clases.security.usuarios.domain.galeria;

import com.clases.security.usuarios.domain.shared.dto.GaleriaDto;
import com.clases.security.usuarios.domain.shared.dto.SerieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
