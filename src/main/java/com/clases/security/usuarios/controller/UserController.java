package com.clases.security.usuarios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    //pagina inicial que se mostrara luego de que haya sido logeado correctamente
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

}
