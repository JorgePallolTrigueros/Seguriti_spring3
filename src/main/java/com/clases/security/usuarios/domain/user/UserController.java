package com.clases.security.usuarios.domain.user;

import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.service.ImageStoreService;
import com.clases.security.usuarios.dao.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;


import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UserController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageStoreService imageStoreService;








    //pagina inicial que se mostrara luego de que haya sido logeado correctamente
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("users",userService.findAllUsers());
        return "index";
    }




    //SELECCIONAR LA ID DEL USUARIO Y MANDARLO A EDITAR (Visible para administrador y solo se puede editar uno su propio perfil)
    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "user-edit";

    }




    //SELECCIONAR LA ID DEL USUARIO Y VER EN OTRA PAGINA DETALLE (Visible para todo el mundo)

    @GetMapping("/users/{id}/view")
    public String viewUser(@PathVariable Long id, Model model) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            model.addAttribute("error", "ID User not found.");
            List<UserEntity> userEntities = userRepository.findAll();
            for(UserEntity u: userEntities){
                u.setImagen(imageStoreService.base64("imagenes/foto-usuario_"+u.getId()+".jpg"));
            }
            model.addAttribute("users", userEntities);
            return "user-list";
        }
        UserEntity userEntity = userOpt.get();
        userEntity.setImagen(imageStoreService.base64("imagenes/portada_historia_"+ userEntity.getId()+".jpg"));
        model.addAttribute("user", userEntity);
        return "user-view";
    }




    //GUARDAR LO EDITADO (Visible para administrador y solo se puede editar uno su propio perfil) Ojo esta pagina puede dar el mismo
    //problema que las novelas siempre que se edite y en la edicion se suba una imagen este crea un registro nuevo


    @PostMapping(value ="/users" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveUser(@RequestParam("image") MultipartFile file, @ModelAttribute("user") UserEntity userEntity) {

        System.out.println("Guardando User");
        System.out.println(userEntity);
        System.out.println("User");
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
        userEntity = userRepository.saveAndFlush(userEntity);
        userEntity.getId();
        imageStoreService.save(file,"portada_user_"+ userEntity.getId()+".jpg");
        return "redirect:/user-list";
    }







    //SELECCIONAR LA ID DEL USUARIO Y MANDARLO A BORRAR (Visible para administrador y solo se puede editar uno su propio perfil)

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable Long id){

        userRepository.deleteById(id);
        return "redirect:/user-list-administrado";

    }

    //SELECCIONAR ESTO Y SE CARGA TODOS LOS USUARIAS SOLO USABLE POR EL ADMINISTRADOR
    @GetMapping("/users/delete")
    public String deleteUsers() {
        userRepository.deleteAll();
        return "redirect:/user-list";
    }


    //UN ENLACE EN EL MENU Y MANDAR AL FORMULARIO DE CORREO


    @GetMapping("/correoproteger")
    public String newCorreoprotegido(Model model) {
        return "correoproteger";
    }

    //ENVIAR UN CORREO DEL FORMULARIO





















    // PARTE NO PROTEGIDA

    @GetMapping("/correosinproteger")
    public String newCorreosinproteger(Model model) {return "correosinproteger";}

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user-edit";}

    @GetMapping("/no-protegida")
    public String noProtegida() { return "no-protegida"; }

    @GetMapping("/no-protegida-correo")
    public String noProtegidacorreo() {
        return "no-protegida-correo";
    }

    @GetMapping("/sobrenosotros-desprotegida")
    public String sobrenosotrosdesprotegida() {
        return "sobrenosotros-desprotegida";
    }

    @GetMapping("/listapelis-desprotegida")
    public String listapelisdesprotegida() {
        return "listapelis-desprotegida";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }


}
