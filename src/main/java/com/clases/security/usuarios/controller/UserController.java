package com.clases.security.usuarios.controller;

import com.clases.security.usuarios.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    //pagina inicial que se mostrara luego de que haya sido logeado correctamente
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("users",userService.findAllUsers());
        return "index";
    }

    //SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR

    //GUARDAR LO EDITADO

    //SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE

    //GUARDAR LO EDITADO

    //ENLACE A REGISTRO

    //GRABAR LO REGISTRADO

    //SELECCIONAR LA ID DEL USUARIO Y MANDARLO A BORRAR

    //UN ENLACE EN EL MENU Y MANDAR AL FORMULARIO DE CORREO

    //ENVIAR UN CORREO DEL FORMULARIO


    //EDITAR O BORRAR DIRECCION



    // PARTE NO PROTEGIDA


    //pagina no protegida
    @GetMapping("/no-protegida")
    public String noProtegida() { return "no-protegida"; }

    @GetMapping("/no-protegida-correo")
    public String noProtegidacorreo() {
        return "no-protegida-correo";
    }

    @GetMapping("/sobrenosotros-desprotegida")
    public String sobrenosotrosdesprotegida() {
        return "sobrenosotros-desprotegida";
    }

    @GetMapping("/listapelis-desprotegida")
    public String listapelisdesprotegida() {
        return "listapelis-desprotegida";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }


}
