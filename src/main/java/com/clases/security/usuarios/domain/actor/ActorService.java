package com.clases.security.usuarios.domain.actor;

import com.clases.security.usuarios.domain.shared.dto.ActorDto;
import com.clases.security.usuarios.domain.shared.dto.SerieDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorService {



    private SerieDto idactor;


    public List<ActorDto> findAllActors(){

        List<ActorDto> actors = new ArrayList<>();

        for(int i=0;i<10;i++){
            actors.add(new ActorDto((long)i,"actor "+i,"",idactor,"") );
        }

        return actors;
    }

}
