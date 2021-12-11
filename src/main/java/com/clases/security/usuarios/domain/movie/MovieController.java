package com.clases.security.usuarios.domain.movie;
import com.clases.security.usuarios.dao.entity.GalleryEntity;
import com.clases.security.usuarios.dao.entity.MovieEntity;
import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.domain.shared.dto.MovieDto;
import com.clases.security.usuarios.domain.movie.MovieService;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import com.clases.security.usuarios.domain.shared.dto.GalleryDto;
import com.clases.security.usuarios.domain.user.UserService;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
    private  MovieRepository movieRepository;

    @Autowired
    private MovieSreviceAPI movieServiceAPI;

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


    @GetMapping("/pelis-movie")
    public String pelismovie(Model model) {
        log.info(AppUtil.getMethodWithClass());
        model.addAttribute("movies",movieService.findAllMovies());
        return "movie-list1";
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

    @GetMapping("/movies/{id}/borrar")
    public String borrar(@PathVariable Long id, Model model) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        return movieService.deleteMovie(id,model);
    }


    @GetMapping("/movies/{id_movie}/nuevagaleria")
    public String nuevagaleria(@PathVariable Long id_movie, Model model) {
        //establecer el id de la historia al modelo
        model.addAttribute("id_movie",id_movie);

        //crear el personaje
        GalleryEntity gallery = new GalleryEntity();
        //asignarle pelicula
        gallery.setMovie(movieRepository.findById(id_movie).get());
        //se llena galeria en el modelo
        model.addAttribute("gallery", gallery);
        return "GaleryMovie-New";
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
    }


    @PostMapping("/movies/new")
    public String MovieNew(Model model, @ModelAttribute("movie") MovieDto movieDto) {
        final Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Optional rol = currentUser.getAuthorities().stream().findFirst();
        log.info("user: "+currentUser.getName());
        log.info("rol: "+rol.get().toString().toUpperCase());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("currentRol", rol.get().toString());
        log.info(AppUtil.getMethodWithClass());
        log.info("MOVIE DTO: {}",movieDto);
        return "Movie-new";
    }


    @GetMapping("/nuevoMovie")
    public String registro(Model model)
    {
        model.addAttribute("movie",new MovieDto());
        return "Movie-new";
    }


    @GetMapping("/moviepaginada")
    public String finnAll (@RequestParam Map <String, Object> params, Model model){
        int page = params.get("page") !=null ? (Integer.valueOf(params.get("page").toString()) - 1) : 0;
        PageRequest pageRequest = PageRequest.of(page, 2);
        Page<MovieEntity> pageMovies = movieServiceAPI.gettAll(pageRequest);
        int totalPage =  pageMovies.getTotalPages();
        if (totalPage >0) {
            List<Integer> pages = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pages", pages);
        }
        model.addAttribute("list", pageMovies.getContent());
        return "movie-list1";
    }


    @PostMapping("/movies/save")
    public String saveMovie(Model model, @ModelAttribute("movie") MovieDto movieDto) {
        log.info("saveMovie "+movieDto.toString());
        return movieService.saveMovie(model, movieDto);
    }
}

