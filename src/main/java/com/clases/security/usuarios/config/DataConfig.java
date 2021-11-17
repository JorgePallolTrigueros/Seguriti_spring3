package com.clases.security.usuarios.config;

import com.clases.security.usuarios.dao.entity.AddressEntity;
import com.clases.security.usuarios.dao.entity.UserEntity;
import com.clases.security.usuarios.dao.repository.UserRepository;
import com.clases.security.usuarios.util.AppUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DataConfig {


    private final Logger log = LoggerFactory.getLogger(getClass());
    private static boolean initialized = true;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initData(){
        if(initialized) return;
        log.info(AppUtil.getMethodWithClass());
        for(int i=0;i<10;i++){
            UserEntity userEntity = new UserEntity("User"+i,"ADMIN","ACTIVO");

            AddressEntity address = new AddressEntity();
            address.setAddress("Calle Figueroa");
            address.setUser(userEntity);
            userEntity.setAddress(address);

            userRepository.save(userEntity);
        }
        initialized = true;
    }

}
