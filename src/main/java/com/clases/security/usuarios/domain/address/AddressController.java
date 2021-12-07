package com.clases.security.usuarios.domain.address;

import com.clases.security.usuarios.domain.shared.dto.AddressDto;
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

/**
 *
 * Listar usuarios          v1/user/
 * Listar usuario con id    v1/user/{id}
 */

@Controller
public class AddressController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AddressService addressService;
    private final UserService userService;
    public AddressController(AddressService addressService,UserService userService) {
        this.userService = userService;
        this.addressService = addressService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     */
    @GetMapping("/adressindex")
    public String addressPage(Model model) {
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
        model.addAttribute("addresss",addressService.findAllAddresss());
        return "address-list";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/addresss/{id}/view")
    public String viewAddress(@PathVariable Long id, Model model) {
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
        return addressService.viewAddress(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/addresss/{id}/edit")
    public String viewAddressEdit(@PathVariable Long id, Model model) {
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
        return addressService.viewAddressEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     */
    @PostMapping("/addresss/edit")
    public String editAddress(Model model, @ModelAttribute("address") AddressDto addressDto) {
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
        log.info("GALERIA DTO: {}",addressDto);
        return addressService.editAddress(model,addressDto);
    }

}