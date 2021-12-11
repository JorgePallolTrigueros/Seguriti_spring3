package com.clases.security.usuarios.domain.otros;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import com.clases.security.usuarios.domain.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@Controller
public class OtrosController {

        private final Logger log = LoggerFactory.getLogger(getClass());
        private final OtrosService otrosService;
        private final UserService userService;

        public OtrosController( OtrosService otrosService, UserService userService) {
            this.otrosService= otrosService;
            this.userService = userService;

        }

// Las paginas sin proteger

    @GetMapping("/sobrenosotros1")
    public String sobrenosotrosn() {
        return "sobrenosotrosn";
    }


    @GetMapping("/correo-list")
    public String correolist() {
        return "correo-list";
    }





    @GetMapping("/registro")
    public String registro(Model model)
    {
        model.addAttribute("user",new UserDto());
        return "User-new";
    }






// Las paginas protegidas


    @GetMapping("/sobrenosotros")
    public String sobrenosotros(Model model) {
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

        return "sobrenosotros";
    }


    @GetMapping("/correo")
    public String correo(Model model) {
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

        return "correo";
    }



    @GetMapping("/correo1")
    public String correo1(Model model) {


        return "correo1";
    }


    }
