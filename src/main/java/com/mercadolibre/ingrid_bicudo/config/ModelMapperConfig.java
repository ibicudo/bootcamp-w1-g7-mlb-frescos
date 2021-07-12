package com.mercadolibre.ingrid_bicudo.config;

import com.mercadolibre.ingrid_bicudo.dtos.BatchDTO;
import com.mercadolibre.ingrid_bicudo.dtos.BatchWithoutNumberDTO;
import com.mercadolibre.ingrid_bicudo.dtos.SectionDTO;
import com.mercadolibre.ingrid_bicudo.model.Batch;
import com.mercadolibre.ingrid_bicudo.model.Section;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.addMappings(new PropertyMap<SectionDTO, Section>() {
            @Override
            protected void configure() {
                map().setCode(source.getSectionCode());
            }
        });

        modelMapper.addMappings(new PropertyMap<BatchWithoutNumberDTO, Batch>() {
            @Override
            protected void configure() {
                map().getInboundOrder().setOrderDate(null);
            }
        });

        modelMapper.addMappings(new PropertyMap<BatchDTO, Batch>() {
            @Override
            protected void configure() {
                map().getInboundOrder().setOrderDate(null);
            }
        });

        return modelMapper;
    }


}
