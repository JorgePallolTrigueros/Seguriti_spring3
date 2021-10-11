package com.clases.security.usuarios.domain.galeria;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class GaleriaController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /*
    @Autowired
    private GaleriaService galeriaService;

    @Autowired
    private GaleriaRepository galeriaRepository;

    @Autowired
    private ImageStoreService  imageStoreService;



//Grabar Multiples Galerias por Serie

    @PostMapping(value ="/galerias" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveGaleria(@RequestParam("image") MultipartFile[] files, @ModelAttribute("galeria") Galeria galeria, @ModelAttribute("idHistoria") Long idHistoria) throws IOException, NoSuchAlgorithmException {
        System.out.println("Guardando Galeria");

        List<Galeria> galeriaList = new ArrayList<>();
        //si el archivo no es nulo y no esta vacio
        if(files!=null && files.length>0){
            //para cada uno de los archivos seleccionados se obtiene el hash
            for(MultipartFile file: files){

                //es el identificador unico de cada archivo
                String hash = AppUtil.getFileChecksum(file);

                //comprobando que la imagen no exista dentro de la galeria con el respectivo id de historia
                if(!galeriaRepository.imageExistInHistoriaWithHash(idHistoria,hash)){

                    String name = "historia_"+idHistoria+"_"+hash+".jpg";
                    System.out.println("image: "+file.getName()+"  , original name:  "+file.getOriginalFilename()+" , content type: "+file.getContentType()+" , empty: "+file.isEmpty()+" , size: "+file.getSize());

                    //creando el objeto que contendra la informacion de cada una de las imagenes
                    Galeria imgGaleria = new Galeria();
                    //asociando la id de historia a la galeria
                    imgGaleria.setSerieWithId(idHistoria);
                    //guardando datos de la imagen
                    imgGaleria.setHash(hash);
                    //guardando la ruta de la imagen
                    imgGaleria.setRuta(name);

                    imageStoreService.save(file,name);

                    //guardando en lista
                    galeriaList.add(imgGaleria);
                }else{
                    System.out.println("Imagen : "+hash+" ya existe dentro de galeria con historia: "+idHistoria);
                }
            }

            //si se han agregado imagenes para guardarlas
            if(galeriaList.size()>0){
                System.out.println("Guardandado: "+galeriaList.size()+" imagenes");
                galeriaRepository.saveAll(galeriaList);
            }else{
                System.out.println("No hay imagenes nuevas que guardar");
            }


        }

        galeriaRepository.save(galeria);
        return "redirect:/serie-view";
    }*/

}