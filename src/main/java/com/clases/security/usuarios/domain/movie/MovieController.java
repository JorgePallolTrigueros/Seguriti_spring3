package com.clases.security.usuarios.domain.movie;
import com.clases.security.usuarios.domain.shared.dto.MovieDto;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import com.clases.security.usuarios.domain.user.UserService;
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
//@RequestMapping("v1/movie")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
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
        model.addAttribute("movies",movieService.findAllMovies());
        return "index";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/movies/{id}/view")
    public String viewMovie(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return movieService.viewMovie(id,model);
    }


    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/movies/{id}/edit")
    public String viewMovieEdit(@PathVariable Long id, Model model) {
        log.info(AppUtil.getMethodWithClass());
        return movieService.viewMovieEdit(id,model);
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
     * @param model
     * @return
     */
    @PostMapping("/movies/edit")
    public String editMovie(Model model, @ModelAttribute("movie") MovieDto movieDto) {
        log.info(AppUtil.getMethodWithClass());
        log.info("USER DTO: {}",movieDto);
        return movieService.editMovie(model,movieDto);
    }  }