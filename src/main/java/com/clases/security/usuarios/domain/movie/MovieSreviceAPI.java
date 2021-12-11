package com.clases.security.usuarios.domain.movie;

import com.clases.security.usuarios.dao.entity.MovieEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieSreviceAPI {

    Page<MovieEntity> gettAll (Pageable pageable);

}
