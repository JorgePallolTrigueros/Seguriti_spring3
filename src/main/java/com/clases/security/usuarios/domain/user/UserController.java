package com.clases.security.usuarios.domain.user;

import com.clases.security.usuarios.domain.shared.dto.UserDto;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homePage(Model model) {
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("users",userService.findAllUsers());
        return "index";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/users/{id}/view")
    public String viewUser(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return userService.viewUser(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/users/{id}/edit")
    public String viewUserEdit(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return userService.viewUserEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     * @param model
     * @return
     */
    @PostMapping("/users/edit")
    public String editUser(Model model, @ModelAttribute("user")UserDto userDto) {
        log.info(AppUtil.getMethodWithClass());
        log.info("USER DTO: {}",userDto);
        return userService.editUser(model,userDto);
    }

    /*













    //SELECCIONAR ESTO Y SE CARGA TODOS LOS USUARIAS SOLO USABLE POR EL ADMINISTRADOR



    //UN ENLACE EN EL MENU Y MANDAR AL FORMULARIO DE CORREO



    //ENVIAR UN CORREO DEL FORMULARIO





















    // PARTE NO PROTEGIDA

    @GetMapping("/correosinproteger")
    public String newCorreosinproteger(Model model) {return "correosinproteger";}

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user-edit";}

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


    */
}
