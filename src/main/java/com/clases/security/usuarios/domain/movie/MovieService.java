package com.clases.security.usuarios.domain.movie;

import com.clases.security.usuarios.dao.entity.MovieActorEntity;
import com.clases.security.usuarios.dao.entity.MovieEntity;
import com.clases.security.usuarios.dao.entity.MovieUserEntity;
import com.clases.security.usuarios.dao.repository.MovieActorRepository;
import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.dao.repository.GalleryRepository;
import com.clases.security.usuarios.dao.repository.MovieUserRepository;
import com.clases.security.usuarios.domain.shared.dto.MovieActorDto;
import com.clases.security.usuarios.domain.shared.dto.MovieUserDto;
import com.clases.security.usuarios.domain.shared.dto.MovieDto;
import com.clases.security.usuarios.domain.shared.dto.GalleryDto;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    //TODO aqui se agrega la relacion de actores en esa pelicula
    private final MovieActorRepository movieActorRepository;
    private final MovieRepository movieRepository;
    private final GalleryRepository galleryRepository;
    private final MovieUserRepository movieUserRepository;
    private final ModelMapper mapper;

    //TODO agregando el nuevo repository
    public MovieService(MovieUserRepository movieUserRepository,
                        MovieActorRepository movieActorRepository,
                        GalleryRepository galleryRepository,
                        MovieRepository movieRepository,
                        ModelMapper mapper)
    {
        this.movieActorRepository = movieActorRepository;
        this.movieRepository = movieRepository;
        this.galleryRepository =galleryRepository;
        this.movieUserRepository=movieUserRepository;
        this.mapper = mapper;
    }


    public List<MovieDto> findAllMovies(){
        return movieRepository
                .findAll() //llamar al repositorio que nos devuelve una lista
                .stream() // con stream lo convierto en un flujo que puedo trabajar
                .map( movieEntity -> mapper.map(movieEntity,MovieDto.class) ) //mapeo cada elemento a MovieDto.class
                .collect(Collectors.toList());//recolectar los resultados y devolver una lista
    }

    /**
     * Buscar usuario por id
     * @param id
     * @param model
     * @return
     */
    public String viewMovie(Long id, Model model) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);

        //TODO buscando en movieActorRepository por el ID de la pelicula por que ya lo conozco
        if(movieEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("movie", mapper.map(movieEntityOptional.get(), MovieDto.class) );

            //TODO buscando la lista de actores y participacion en esa pelicula
            List<MovieActorDto> movieActorDtoList = movieActorRepository
                    .findAllMovieActorByIdMovie(id)//devolver lista de movieActor ojo que es entity hay que convertila a dto
                    .stream()//trabajamos con el flujo stream
                    .map(m -> mapper.map(m,MovieActorDto.class))
                    .collect(Collectors.toList());
            model.addAttribute("movieActors", movieActorDtoList );
            //TODO como es una lista especifico con plural moveActorSSSSS
            //TODO buscando la lista de galeria en esa pelicula
            List<GalleryDto> galleryDtoList = galleryRepository
                    .findAllGalleryByIdMovie(id)//devolver lista de Galery ojo que es entity hay que convertila a dto
                    .stream()//trabajamos con el flujo stream
                    .map(m -> mapper.map(m,GalleryDto.class))
                    .collect(Collectors.toList());
            model.addAttribute("gallerys", galleryDtoList );
            //TODO como es una lista especifico con plural moveActorSSSSS
            List<MovieUserDto> movieUserDtoList = movieUserRepository
                    .findAllMovieUserByIdMovie(id)//devolver lista de movieActor ojo que es entity hay que convertila a dto
                    .stream()//trabajamos con el flujo stream
                    .map(m -> mapper.map(m,MovieUserDto.class))
                    .collect(Collectors.toList());
            model.addAttribute("movieUsers", galleryDtoList );
            //TODO falta DTO






            return "movie-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String deleteMovie(Long id, Model model) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);

        if(movieEntityOptional.isPresent()){
            //si esta presente
            movieRepository.deleteById(id);
            return "index";
        }else{
            //si no esta presente
            //devolver a una pagina de error no se encuentra
            return "index";
        }
    }


    public String editMovie(Model model,MovieDto movieDto) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(movieDto.getId());

        if(movieEntityOptional.isPresent()){
            //si esta presente
            MovieEntity  movieEntity = movieEntityOptional.get();
            //mapear de un objeto ya existente los datos de otro
            mapper.map(movieDto,movieEntity);
            //guardar
            movieEntity = movieRepository.saveAndFlush(movieEntity);
            //agregar al model attribute
            model.addAttribute("movie", mapper.map(movieEntity, MovieDto.class) );
            //retornar la vista
            return "movie-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String viewMovieEdit(Long id, Model model) {
        Optional<MovieEntity> movieEntityOptional = movieRepository.findById(id);

        if(movieEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("movie", mapper.map(movieEntityOptional.get(), MovieDto.class) );
            return "movie-edit";
        }else{
            //si no esta presente
            return "index";
        }
    }
}
