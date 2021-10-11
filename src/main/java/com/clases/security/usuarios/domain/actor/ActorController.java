package com.clases.security.usuarios.domain.actor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


@Controller
public class ActorController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /*

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
        return "redirect:/actor-list";
    }

    //AQUI BORRAMOS TODOS EL ACTOR ELEGIDO

    @GetMapping("/actor/{id}/delete")
    public String deleteSerie(@PathVariable Long id){
        actorRepository.deleteById(id);
        return "redirect:/actor-list";
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
        return "actor-view";
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
        return "redirect:/actor-list";
    }



    @GetMapping("/actor/{id}/edit")
    public String editPersonaje(@PathVariable Long id, Model model) {
        model.addAttribute("personaje", actorRepository.findById(id).get());
        return "actor-edit";

    }
    */


}
