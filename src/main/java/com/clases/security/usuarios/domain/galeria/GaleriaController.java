package com.clases.security.usuarios.domain.galeria;

import com.clases.security.usuarios.domain.shared.dto.GaleriaDto;
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
public class GaleriaController {


    private final Logger log = LoggerFactory.getLogger(getClass());
    private final GaleriaService galeriaService;

    public GaleriaController(GaleriaService galeriaService) {
        this.galeriaService = galeriaService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     * RUTA: /
     * @param model
     * @return
     */
    @GetMapping("/galeriaindex")
    public String GaleriaPage(Model model) {
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("galerias",galeriaService.findAllGalerias());
        return "galeria-list";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/galerias/{id}/view")
    public String viewGaleria(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return galeriaService.viewGaleria(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/galerias/{id}/edit")
    public String viewGaleriaEdit(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return galeriaService.viewGaleriaEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     * @param model
     * @return
     */
    @PostMapping("/galerias/edit")
    public String editGaleria(Model model, @ModelAttribute("galeria") GaleriaDto galeriaDto) {
        log.info(AppUtil.getMethodWithClass());
        log.info("GALERIA DTO: {}",galeriaDto);
        return galeriaService.editGaleria(model,galeriaDto);
    }

}