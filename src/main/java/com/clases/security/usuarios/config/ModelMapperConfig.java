package com.clases.security.usuarios.config;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion del mapeo
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);//omitir datos nulos
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());//omitir datos nulos
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);//que el nombre del campo sea identico al nombre del otro campo
        return modelMapper;
    }
}

