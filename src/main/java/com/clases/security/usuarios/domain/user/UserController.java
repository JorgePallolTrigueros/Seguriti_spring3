package com.clases.security.usuarios.domain.user;

import com.clases.security.usuarios.domain.shared.dto.UserDto;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    public String homePage(HttpSession session, Model model) {
        log.info("Session: "+session.getId()+" "+session.toString());
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @GetMapping("/prohibido")
    public String forbiden() {
        return "prohibido";
    }



    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/users/{id}/view")
    public String viewUser(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return userService.viewUser(id, model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/users/{id}/edit")
    public String viewUserEdit(@PathVariable Long id, Model model) {
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
        log.info(AppUtil.getMethodWithClass());
        log.info("USER DTO: {}", userDto);
        return userService.editUser(model, userDto);
    }


    @GetMapping("/correos")
    public String newCorreo(Model model) {
        return "correo";
    }


    @GetMapping("/users/new")
    public String newUser() {
        return "user-edit";
    }













    @GetMapping("/sobrenosotros")
    public String sobrenosotros() {
        return "sobrenosotros";
    }
}
