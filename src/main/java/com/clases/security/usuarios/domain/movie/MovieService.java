package com.clases.security.usuarios.domain.movie;

import com.clases.security.usuarios.dao.entity.MovieEntity;
import com.clases.security.usuarios.dao.repository.MovieRepository;
import com.clases.security.usuarios.domain.shared.dto.MovieDto;
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
    private final MovieRepository movieRepository;
    private final ModelMapper mapper;

    public MovieService(MovieRepository movieRepository, ModelMapper mapper) {
        this.movieRepository = movieRepository;
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

        if(movieEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("movie", mapper.map(movieEntityOptional.get(), MovieDto.class) );
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
