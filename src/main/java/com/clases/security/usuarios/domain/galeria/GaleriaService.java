package com.clases.security.usuarios.domain.galeria;

import com.clases.security.usuarios.domain.shared.dto.GaleriaDto;
import com.clases.security.usuarios.domain.shared.dto.GaleriaDto;
import com.clases.security.usuarios.dao.entity.GalleryEntity;
import com.clases.security.usuarios.dao.repository.GalleryRepository;
import com.clases.security.usuarios.domain.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class GaleriaService {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final GalleryRepository galeriaRepository;
    private final ModelMapper mapper;

    public GaleriaService(GalleryRepository galeriaRepository, ModelMapper mapper) {
        this.galeriaRepository = galeriaRepository;
        this.mapper = mapper;
    }


    /**
     * Devuelve la lista de usuarios
     * @return List<GaleriaDto>
     */
    public List<GaleriaDto> findAllGalerias(){
        return galeriaRepository
                .findAll() //llamar al repositorio que nos devuelve una lista
                .stream() // con stream lo convierto en un flujo que puedo trabajar
                .map( galeriaEntity -> mapper.map(galeriaEntity,GaleriaDto.class) ) //mapeo cada elemento a GaleriaDto.class
                .collect(Collectors.toList());//recolectar los resultados y devolver una lista
    }

    /**
     * Buscar usuario por id
     * @param id
     * @param model
     * @return
     */
    public String viewGaleria(Long id, Model model) {
        Optional<GalleryEntity> galeriaEntityOptional = galeriaRepository.findById(id);

        if(galeriaEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("galeria", mapper.map(galeriaEntityOptional.get(), GaleriaDto.class) );
            return "galeria-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String deleteGaleria(Long id, Model model) {
        Optional<GalleryEntity> galeriaEntityOptional = galeriaRepository.findById(id);

        if(galeriaEntityOptional.isPresent()){
            //si esta presente
            galeriaRepository.deleteById(id);
            return "index";
        }else{
            //si no esta presente
            //devolver a una pagina de error no se encuentra
            return "index";
        }
    }


    public String editGaleria(Model model,GaleriaDto galeriaDto) {
        Optional<GalleryEntity> galeriaEntityOptional = galeriaRepository.findById(galeriaDto.getId());

        if(galeriaEntityOptional.isPresent()){
            //si esta presente
            GalleryEntity  galeriaEntity = galeriaEntityOptional.get();
            //mapear de un objeto ya existente los datos de otro
            mapper.map(galeriaDto,galeriaEntity);
            //guardar
            galeriaEntity = galeriaRepository.saveAndFlush(galeriaEntity);
            //agregar al model attribute
            model.addAttribute("galeria", mapper.map(galeriaEntity, GaleriaDto.class) );
            //retornar la vista
            return "galeria-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String viewGaleriaEdit(Long id, Model model) {
        Optional<GalleryEntity> galeriaEntityOptional = galeriaRepository.findById(id);

        if(galeriaEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("galeria", mapper.map(galeriaEntityOptional.get(), GaleriaDto.class) );
            return "galeria-edit";
        }else{
            //si no esta presente
            return "index";
        }
    }
}

