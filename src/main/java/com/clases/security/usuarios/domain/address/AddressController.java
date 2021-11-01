package com.clases.security.usuarios.domain.address;

import com.clases.security.usuarios.domain.shared.dto.AddressDto;
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
public class AddressController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     * RUTA: /
     * @param model
     * @return
     */
    @GetMapping("/adressindex")
    public String addressPage(Model model) {
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("addresss",addressService.findAllAddresss());
        return "address-list";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/addresss/{id}/view")
    public String viewAddress(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return addressService.viewAddress(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/addresss/{id}/edit")
    public String viewAddressEdit(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return addressService.viewAddressEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     * @param model
     * @return
     */
    @PostMapping("/addresss/edit")
    public String editAddress(Model model, @ModelAttribute("address") AddressDto addressDto) {
        log.info(AppUtil.getMethodWithClass());
        log.info("GALERIA DTO: {}",addressDto);
        return addressService.editAddress(model,addressDto);
    }

}