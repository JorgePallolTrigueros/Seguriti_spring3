package com.clases.security.usuarios.controller;


import com.clases.security.usuarios.service.ImageStoreService;
import com.clases.security.usuarios.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.clases.security.usuarios.service.ActorService;
import com.clases.security.usuarios.entity.Actor;
import com.clases.security.usuarios.entity.Serie;
import com.clases.security.usuarios.repository.ActorRepository;
import com.clases.security.usuarios.repository.SerieRepository;
import java.io.IOException;
import java.util.Base64;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.clases.security.usuarios.service.ImageStoreService;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class ActorController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private SerieRepository serierepository;

    @Autowired
    private ActorService actorService;

    @Autowired
    private ImageStoreService imageStoreService;


    //AQUI LO QUE HACEMOS ES UNIR UN ACTOR A UNA SERIE QUE TIENE UNA RELACIÓN 1 SERIE MUCHOS ACTORES

    @GetMapping({"/series/{id_serie}/insertactorenserie"})
    public String insertactorenserie(@PathVariable Long id_serie, Model model) {

        //establecer el id de la serie al modelo
        model.addAttribute("idSerie",id_serie);
        //crear el ACTOR
        Actor actor = new Actor();
        //asignarle historia
        actor.setIdserie(serierepository.findById(id_serie).get());
        //se llena ACTOR en el modelo
        model.addAttribute("actor", actor);
        return "insertactorenserie";
    }



    //AQUI bORRAMOS TODOS LOS ACTORES DE UN GOLPE

    @GetMapping("/actor/delete")
    public String deleteActor() {
        actorRepository.deleteAll();
        return "redirect:/actor";
    }

    //AQUI BORRAMOS TODOS EL ACTOR ELEGIDO

    @GetMapping("/actor/{id}/delete")
    public String deleteSerie(@PathVariable Long id){
        actorRepository.deleteById(id);
        return "redirect:/actor";
    }

    //AQUI DETALLE DEL ACTOR ELEGIDO

    @GetMapping("/actor/{id}/view")
    public String viewSerie(@PathVariable Long id, Model model) {
        Optional<Actor> actorOpt = actorRepository.findById(id);
        if (!actorOpt.isPresent()) {
            model.addAttribute("error", "ID Personaje not found.");
            model.addAttribute("actor", actorRepository.findAll());
            return "actor-list";
        }
        model.addAttribute("actor", actorOpt.get());
        return "personaje-view";
    }


    //AQUI salvar los ACTORES


    @PostMapping(value ="/actor" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveActor(@RequestParam("image") MultipartFile file, @ModelAttribute("actor") Actor actor, @ModelAttribute("idSerie") Long idSerie) {
        System.out.println("Guardando Actor");
        System.out.println(actor);
        System.out.println("Serie");

        if(idSerie!=null){
            System.out.println("Se lleno la idSerie "+idSerie);
            actor.setIdserie(new Serie(idSerie));
        }

        //si el archivo no es nulo y no esta vacio
        if(file!=null && !file.isEmpty()){
            System.out.println("image: "+file.getName()+"  , original name:  "+file.getOriginalFilename()+" , content type: "+file.getContentType()+" , empty: "+file.isEmpty()+" , size: "+file.getSize());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            //si el nombre del archivo contiene dos puntos
            if(fileName.contains(".."))
            {
                System.out.println("archivo no valido");
            }else{
                try {
                    actor.setImagen(Base64.getEncoder().encodeToString(file.getBytes()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        actorRepository.save(actor);
        return "redirect:/actor";
    }



    @GetMapping("/actor/{id}/edit")
    public String editPersonaje(@PathVariable Long id, Model model) {
        model.addAttribute("personaje", actorRepository.findById(id).get());
        return "actor-edit";

    }

}
