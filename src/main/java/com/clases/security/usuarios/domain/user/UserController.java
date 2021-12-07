package com.clases.security.usuarios.domain.user;

import com.clases.security.usuarios.domain.shared.dto.UserDto;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 *
 * Listar usuarios          v1/user/
 * Listar usuario con id    v1/user/{id}
 */

@Controller
//@RequestMapping("v1/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     * RUTA: /
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homePage( Model model) {
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

        return "index";
    }

    @GetMapping("/prohibido")
    public String forbiden() {
        return "prohibido";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }



    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/users/{id}/view")
    public String viewUser(@PathVariable Long id, Model model) {
        //TODO ESTO VA SOBRE AUTENTIFICACION
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());



        log.info(AppUtil.getMethodWithClass());
        return userService.viewUser(id, model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/users/{id}/edit")
    public String viewUserEdit(@PathVariable Long id, Model model) {
        //TODO ESTO VA SOBRE AUTENTIFICACION
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        return userService.viewUserEdit(id, model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     *
     * @param model
     * @return
     */
    @PostMapping("/users/edit")
    public String editUser(Model model, @ModelAttribute("user") UserDto userDto) {
        //TODO ESTO VA SOBRE AUTENTIFICACION
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        log.info("USER DTO: {}", userDto);
        return userService.editUser(model, userDto);
    }


}
