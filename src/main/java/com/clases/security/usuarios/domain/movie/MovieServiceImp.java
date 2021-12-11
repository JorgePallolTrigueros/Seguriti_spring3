package com.clases.security.usuarios.domain.movie;

import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.dao.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImp implements MovieSreviceAPI {

    @Autowired
    private MovieRepository movieRepository;




    @Override
    public Page<MovieEntity> gettAll(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }
}
