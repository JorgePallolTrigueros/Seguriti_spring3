package com.clases.security.usuarios.domain.actor;


import com.clases.security.usuarios.domain.shared.dto.ActorDto;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ActorController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     * RUTA: /
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homePage(Model model) {
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("actors",actorService.findAllActors());
        return "index";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/actors/{id}/view")
    public String viewActor(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return actorService.viewActor(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/actors/{id}/edit")
    public String viewActorEdit(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return actorService.viewActorEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     * @param model
     * @return
     */
    @PostMapping("/actors/edit")
    public String editActor(Model model, @ModelAttribute("actor") ActorDto actorDto) {
        log.info(AppUtil.getMethodWithClass());
        log.info("GALERIA DTO: {}",actorDto);
        return actorService.editActor(model,actorDto);
    }

}
