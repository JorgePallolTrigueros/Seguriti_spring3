package com.clases.security.usuarios.controller;


import com.clases.security.usuarios.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.clases.security.usuarios.service.ActorService;

@Controller
public class ActorController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActorService actorService;


}
