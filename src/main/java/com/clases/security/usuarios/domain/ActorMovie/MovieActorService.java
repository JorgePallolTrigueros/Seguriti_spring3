package com.clases.security.usuarios.domain.ActorMovie;

import com.clases.security.usuarios.dao.repository.AddressRepository;
import com.clases.security.usuarios.dao.repository.MovieActorRepository;
import com.clases.security.usuarios.domain.shared.dto.AddressDto;
import com.clases.security.usuarios.domain.shared.dto.MovieActorDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MovieActorService {

    private MovieActorDto idActorMovie;

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MovieActorRepository movieActorRepository;
    private final ModelMapper mapper;

    public MovieActorService(MovieActorRepository movieActorRepository, ModelMapper mapper) {
        this.movieActorRepository = movieActorRepository;
        this.mapper = mapper;
    }
}
