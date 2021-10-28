package com.clases.security.usuarios.domain.address;

import com.clases.security.usuarios.domain.shared.dto.AddressDto;
import com.clases.security.usuarios.dao.entity.AddressEntity;
import com.clases.security.usuarios.dao.repository.AddressRepository;
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
public class AddressService {

    private AddressDto idaddress;

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AddressRepository addressRepository;
    private final ModelMapper mapper;

    public AddressService(AddressRepository addressRepository, ModelMapper mapper) {
        this.addressRepository = addressRepository;
        this.mapper = mapper;
    }


    /**
     * Devuelve la lista de usuarios
     * @return List<AddressDto>
     */
    public List<AddressDto> findAllAddresss(){
        return addressRepository
                .findAll() //llamar al repositorio que nos devuelve una lista
                .stream() // con stream lo convierto en un flujo que puedo trabajar
                .map( addressEntity -> mapper.map(addressEntity,AddressDto.class) ) //mapeo cada elemento a AddressDto.class
                .collect(Collectors.toList());//recolectar los resultados y devolver una lista
    }

    /**
     * Buscar usuario por id
     * @param id
     * @param model
     * @return
     */
    public String viewAddress(Long id, Model model) {
        Optional<AddressEntity> addressEntityOptional = addressRepository.findById(id);

        if(addressEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("address", mapper.map(addressEntityOptional.get(), AddressDto.class) );
            return "address-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String deleteAddress(Long id, Model model) {
        Optional<AddressEntity> addressEntityOptional = addressRepository.findById(id);

        if(addressEntityOptional.isPresent()){
            //si esta presente
            addressRepository.deleteById(id);
            return "index";
        }else{
            //si no esta presente
            //devolver a una pagina de error no se encuentra
            return "index";
        }
    }


    public String editAddress(Model model,AddressDto addressDto) {
        Optional<AddressEntity> addressEntityOptional = addressRepository.findById(addressDto.getId());

        if(addressEntityOptional.isPresent()){
            //si esta presente
            AddressEntity  addressEntity = addressEntityOptional.get();
            //mapear de un objeto ya existente los datos de otro
            mapper.map(addressDto,addressEntity);
            //guardar
            addressEntity = addressRepository.saveAndFlush(addressEntity);
            //agregar al model attribute
            model.addAttribute("address", mapper.map(addressEntity, AddressDto.class) );
            //retornar la vista
            return "address-view";
        }else{
            //si no esta presente
            return "index";
        }
    }

    public String viewAddressEdit(Long id, Model model) {
        Optional<AddressEntity> addressEntityOptional = addressRepository.findById(id);

        if(addressEntityOptional.isPresent()){
            //si esta presente
            model.addAttribute("address", mapper.map(addressEntityOptional.get(), AddressDto.class) );
            return "address-edit";
        }else{
            //si no esta presente
            return "index";
        }
    }
}


