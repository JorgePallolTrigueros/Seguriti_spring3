package com.clases.security.usuarios.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.clases.security.usuarios.service.DireccionService;

@Controller
public class DirectionController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DireccionService directionService;

}
