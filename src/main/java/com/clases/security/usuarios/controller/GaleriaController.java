package com.clases.security.usuarios.controller;

import com.clases.security.usuarios.service.GaleriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GaleriaController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GaleriaService galeriaService;



}