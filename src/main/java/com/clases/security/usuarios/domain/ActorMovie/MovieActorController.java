package com.clases.security.usuarios.domain.ActorMovie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


@Controller
public class MovieActorController {



    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MovieActorService movieActorService;

    public MovieActorController(MovieActorService movieActorService) {
        this.movieActorService = movieActorService;
    }
}
