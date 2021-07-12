package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.section;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.SectionUpdateDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Account;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Section;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.Supervisor;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.SectionRepository;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.SupervisorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService{


    private final SupervisorRepository supervisorRepository;

    private final SectionRepository sectionRepository;

    private final ModelMapper modelMapper;


    public SectionServiceImpl(SupervisorRepository supervisorRepository, SectionRepository sectionRepository, ModelMapper modelMapper) {
        this.supervisorRepository = supervisorRepository;
        this.sectionRepository = sectionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Section updateCapacitySection(SectionUpdateDTO sectionUpdateDTO, Account account) {

        Section section = this.sectionRepository.findById(sectionUpdateDTO.getCode())
                .orElseThrow(() -> new NotFoundException("Section does not exist"));

        Supervisor supervisor = this.supervisorRepository.findById(account.getId())
                .orElseThrow(() -> new NotFoundException("Supervisor not found"));

        Section newInboundOrder = modelMapper.map(sectionUpdateDTO, Section.class);
        sectionRepository.save(newInboundOrder);

        return newInboundOrder;
    }


}
