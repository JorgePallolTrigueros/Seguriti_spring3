package com.clases.security.usuarios.domain.otros;
import com.clases.security.usuarios.dao.repository.MovieActorRepository;
import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.dao.repository.MovieUserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OtrosService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ModelMapper mapper;

    //TODO agregando el nuevo repository
    public OtrosService(ModelMapper mapper)
    {
        this.mapper = mapper;
    }
}
