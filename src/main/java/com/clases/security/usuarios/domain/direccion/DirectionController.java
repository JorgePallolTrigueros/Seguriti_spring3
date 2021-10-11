package com.clases.security.usuarios.domain.direccion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.clases.security.usuarios.domain.direccion.DireccionService;

@Controller
public class DirectionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DireccionService directionService;

}
