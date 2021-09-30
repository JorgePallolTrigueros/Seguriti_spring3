package com.clases.security.usuarios.service;
import com.clases.security.usuarios.dto.ActorDto;
import com.clases.security.usuarios.dto.SerieDto;
import com.clases.security.usuarios.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserService {

    /**
     * Devuelve la lista de usuarios
     * @return List<UserDto>
     */
    public List<UserDto> findAllUsers(){

        List<UserDto> users = new ArrayList<>();

        for(int i=0;i<10;i++){
            users.add( new UserDto((long)i,"usuario "+i,"","ADMIN","ACTIVO",new Date(),"usuario"+i+"@email.com") );
        }

        return users;
    }

}
