package com.clases.security.usuarios.domain.actor;


import com.clases.security.usuarios.domain.shared.dto.ActorDto;
import com.clases.security.usuarios.domain.user.UserService;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ActorController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ActorService actorService;
    private final UserService userService;
    public ActorController(ActorService actorService, UserService userService) {
        this.actorService = actorService;
        this.userService = userService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     */
    @GetMapping("/actorindex")
    public String actorPage(Model model) {
        //TODO saber informacion del usuario autenticado
        //TODO esta informacion solo es accesible en paginas con seguridad
        //TODO por que en las paginas que no tienen seguridad no hay usuario logeado
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("actors",actorService.findAllActors());
        return "actor-list";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/actors/{id}/view")
    public String viewActor(@PathVariable Long id, Model model) {
        //TODO saber informacion del usuario autenticado
        //TODO esta informacion solo es accesible en paginas con seguridad
        //TODO por que en las paginas que no tienen seguridad no hay usuario logeado
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        return actorService.viewActor(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/actors/{id}/edit")
    public String viewActorEdit(@PathVariable Long id, Model model) {
        //TODO saber informacion del usuario autenticado
        //TODO esta informacion solo es accesible en paginas con seguridad
        //TODO por que en las paginas que no tienen seguridad no hay usuario logeado
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        return actorService.viewActorEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     */
    @PostMapping("/actors/edit")
    public String editActor(Model model, @ModelAttribute("actor") ActorDto actorDto) {
        //TODO saber informacion del usuario autenticado
        //TODO esta informacion solo es accesible en paginas con seguridad
        //TODO por que en las paginas que no tienen seguridad no hay usuario logeado
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        log.info("GALERIA DTO: {}",actorDto);
        return actorService.editActor(model,actorDto);
    }

}
