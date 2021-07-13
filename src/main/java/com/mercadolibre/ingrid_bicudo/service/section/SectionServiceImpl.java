package com.mercadolibre.ingrid_bicudo.service.section;

import com.mercadolibre.ingrid_bicudo.dtos.SectionUpdateDTO;
import com.mercadolibre.ingrid_bicudo.exceptions.NotFoundException;
import com.mercadolibre.ingrid_bicudo.model.Account;
import com.mercadolibre.ingrid_bicudo.model.Section;
import com.mercadolibre.ingrid_bicudo.model.Supervisor;
import com.mercadolibre.ingrid_bicudo.model.Warehouse;
import com.mercadolibre.ingrid_bicudo.repository.SectionRepository;
import com.mercadolibre.ingrid_bicudo.repository.SupervisorRepository;
import com.mercadolibre.ingrid_bicudo.repository.WarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl implements SectionService{


    private final SupervisorRepository supervisorRepository;

    private final SectionRepository sectionRepository;

    private final WarehouseRepository warehouseRepository;

    private final ModelMapper modelMapper;


    public SectionServiceImpl(SupervisorRepository supervisorRepository, SectionRepository sectionRepository, WarehouseRepository warehouseRepository, ModelMapper modelMapper) {
        this.supervisorRepository = supervisorRepository;
        this.sectionRepository = sectionRepository;
        this.warehouseRepository = warehouseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Section updateCapacitySection(SectionUpdateDTO sectionUpdateDTO, Account account) {

        Section section = this.sectionRepository.findById(sectionUpdateDTO.getCode())
                .orElseThrow(() -> new NotFoundException("Section does not exist"));

        Warehouse warehouse = this.warehouseRepository.findById(sectionUpdateDTO.getWarehouse().getCode())
                .orElseThrow(() -> new NotFoundException("Warehouse does not exist"));

        Supervisor supervisor = this.supervisorRepository.findById(account.getId())
                .orElseThrow(() -> new NotFoundException("Supervisor not found"));

        Section newInboundOrder = modelMapper.map(sectionUpdateDTO, Section.class);
        sectionRepository.save(newInboundOrder);

        return newInboundOrder;
    }


}
