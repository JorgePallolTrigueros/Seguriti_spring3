package com.clases.security.usuarios.domain.series;

import org.springframework.stereotype.Controller;

@Controller
public class SerieController {

    /*
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SerieService serieService;

    @Autowired
    private SerieRepository serierepository;

    @Autowired
    private ImageStoreService imageStoreService;






    //pagina inicial que se mostrara luego de que haya sido logeado correctamente
    @GetMapping("/serielist")
    public String serielis(Model model) {
        model.addAttribute("series",serieService.findAllSeries());
        return "index";
    }




    //SELECCIONAR LA ID DE LA SERIE Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
    @GetMapping("/seriess/{id}/edit")
    public String editSerie(@PathVariable Long id, Model model) {
        model.addAttribute("serie", serierepository.findById(id).get());
        return "serie-edit";
    }




    //SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE (Visible para todo el mundo)

    @GetMapping("/series/{id}/view")
    public String viewSerie(@PathVariable Long id, Model model) {
        Optional<Serie> serieOpt = serierepository.findById(id);
        if (!serieOpt.isPresent()) {
            model.addAttribute("error", "ID User not found.");
            List<Serie> series = serierepository.findAll();
            for(Serie s:series){
                s.setImagen(imageStoreService.base64("imagenes/foto-serie_"+s.getId()+".jpg"));
            }
            model.addAttribute("series", series);
            return "serie-list";
        }
        Serie serie = serieOpt.get();
        serie.setImagen(imageStoreService.base64("imagenes/portada_historia_"+serie.getId()+".jpg"));
        model.addAttribute("serie", serie);
        return "serie-view";
    }




    //GUARDAR LO EDITADO (Visible para administrador y solo se puede editar uno su propio perfil) Ojo esta pagina puede dar el mismo
    //problema que las novelas siempre que se edite y en la edicion se suba una imagen este crea un registro nuevo


    @PostMapping(value ="/series" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveSerie(@RequestParam("image") MultipartFile file, @ModelAttribute("serie") Serie serie) {

        System.out.println("Guardando Serie");
        System.out.println(serie);
        System.out.println("Serie");
        //si el archivo no es nulo y no esta vacio
        if(file!=null && !file.isEmpty()){
            System.out.println("image: "+file.getName()+"  , original name:  "+file.getOriginalFilename()+" , content type: "+file.getContentType()+" , empty: "+file.isEmpty()+" , size: "+file.getSize());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if(fileName.contains(".."))
            {
                System.out.println("archivo no valido");
            }else{
            }
        }
        serie = serierepository.saveAndFlush(serie);
        serie.getId();
        imageStoreService.save(file,"portada_user_"+serie.getId()+".jpg");
        return "redirect:/serie-list";
    }







    //SELECCIONAR LA ID DEL USUARIO Y MANDARLO A BORRAR (Visible para administrador y solo se puede editar uno su propio perfil)

    @GetMapping("/series/{id}/delete")
    public String deleteSerie(@PathVariable Long id){

        serierepository.deleteById(id);
        return "redirect:/serie-list-administrado";

    }

    //SELECCIONAR ESTO Y SE CARGA TODOS LAS SERIES  SOLO USABLE POR EL ADMINISTRADOR
    @GetMapping("/series/delete")
    public String deleteSeries() {
        serierepository.deleteAll();
        return "redirect:/serie-list";
    }


    */






















}
