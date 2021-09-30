package com.clases.security.usuarios.controller;

import com.clases.security.usuarios.service.SerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SerieController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SerieService serieService;


}
