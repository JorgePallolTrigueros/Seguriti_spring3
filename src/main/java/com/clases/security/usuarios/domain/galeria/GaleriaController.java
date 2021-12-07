package com.clases.security.usuarios.domain.galeria;

import com.clases.security.usuarios.domain.shared.dto.GalleryDto;
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
//@RequestMapping("v1/user")
public class GaleriaController {


    private final Logger log = LoggerFactory.getLogger(getClass());
    private final GaleriaService galeriaService;
    private final UserService userService;

    public GaleriaController(GaleriaService galeriaService,UserService userService) {
        this.userService = userService;
        this.galeriaService = galeriaService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     */
    @GetMapping("/galeriaindex")
    public String GaleriaPage(Model model) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("galerias",galeriaService.findAllGalerias());
        return "galeria-list";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/galerias/{id}/view")
    public String viewGaleria(@PathVariable Long id, Model model) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        return galeriaService.viewGaleria(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     */
    @GetMapping("/galerias/{id}/edit")
    public String viewGaleriaEdit(@PathVariable Long id, Model model) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        return galeriaService.viewGaleriaEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     */
    @PostMapping("/galerias/edit")
    public String editGaleria(Model model, @ModelAttribute("galeria") GalleryDto galleryDto) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();

        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());

        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        log.info("GALERIA DTO: {}", galleryDto);
        return galeriaService.editGaleria(model, galleryDto);
    }











}