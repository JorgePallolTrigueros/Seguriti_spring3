package com.clases.security.usuarios.domain.series;

import com.clases.security.usuarios.domain.shared.dto.SerieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SerieService {

    /**
     * Devuelve la lista de Serie
     * @return List<SerieDto>
     */
    public List<SerieDto> findAllSeries(){

        List<SerieDto> series = new ArrayList<>();

        for(int i=0;i<10;i++){
            series.add( new SerieDto((long)i,"peli"+i,"active"+i,"genero"+i,new Date(),"descripcion"+i,"imagen"+i));
        }




        return series;
    }
}
