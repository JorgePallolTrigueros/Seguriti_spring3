package com.clases.security.usuarios.domain.movie;
import com.clases.security.usuarios.domain.shared.dto.MovieDto;
import com.clases.security.usuarios.domain.movie.MovieService;
import com.clases.security.usuarios.domain.user.UserService;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
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
//@RequestMapping("v1/movie")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MovieService movieService;
    private final UserService userService;



    public MovieController(MovieService movieService,UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    /**
     * Devolver la lista de usuarios al modelAttribute
     * RUTA: /
     * @param model
     * @return
     */
    @GetMapping("/movieindex")
    public String moviePage(Model model) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("movies",movieService.findAllMovies());
        return "movie-list";
    }

    /**
     * SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/movies/{id}/view")
    public String viewMovie(HttpSession session, @PathVariable Long id, Model model) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
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
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
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
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        log.info("MOVIE DTO: {}",movieDto);
        return movieService.editMovie(model,movieDto);
    }  }