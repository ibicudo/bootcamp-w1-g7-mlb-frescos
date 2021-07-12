package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.unit;

import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.dtos.SectionUpdateDTO;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.exceptions.NotFoundException;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.repository.*;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.section.SectionService;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.service.section.SectionServiceImpl;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.MockitoExtension;
import com.mercadolibre.bootcamp_w1_g7_mlb_frescos.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private SupervisorRepository supervisorRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private InboundOrderRepository inboundOrderRepository;

    @Mock
    private BatchRepository batchRepository;


    private ModelMapper modelMapper;

    @InjectMocks
    private SectionServiceImpl sectionService;

    private Supervisor supervisor;
    private SectionUpdateDTO sectionUpdateDTO;
    private Account accountSupervisor;
    private List<Section> sectionList = new ArrayList<>();
    private Section section = new Section();
    private Warehouse warehouse = new Warehouse();

    @BeforeEach
    public void setUp (){
        sectionUpdateDTO = TestUtilsGenerator.createSectionUpdateDTO();
        accountSupervisor = TestUtilsGenerator.createAccountSupervisor();
        warehouse = TestUtilsGenerator.createWarehouse();
        section = new Section();
        section.setCode("OSAF001");
        section.setCategory("FS");
        section.setCapacity(100);
        section.setWarehouse(warehouse);
        modelMapper = TestUtilsGenerator.createModelMapper();
//        section = TestUtilsGenerator.createSection("OSAF001", "FS", 10, new Warehouse("OSAF", "Fullfillment Osasco"));
        supervisor = TestUtilsGenerator.createSupervisor();
        sectionService = new SectionServiceImpl(supervisorRepository, sectionRepository, modelMapper);
    }

    @Test
    void testUpdateSection (){
        //arrange
        when(supervisorRepository.findById(accountSupervisor.getId())).thenReturn(Optional.of(supervisor));
        when(sectionRepository.findById(section.getCode())).thenReturn(Optional.of(section));
        when(sectionRepository.save(any(Section.class))).thenReturn(section);

        //act
        Section response = sectionService.updateCapacitySection(sectionUpdateDTO, accountSupervisor);

        //assert
        assertThat(section).usingRecursiveComparison().isEqualTo(response);

    }

    @Test
    void testUpdateSectionIfDoesNotExistSection (){
        //arrange
        when(supervisorRepository.findById(accountSupervisor.getId())).thenReturn(Optional.of(supervisor));
        when(sectionRepository.findById(section.getCode())).thenReturn(Optional.empty());
        when(sectionRepository.save(any(Section.class))).thenReturn(section);


        //assert
        assertThrows(NotFoundException.class, () -> {
            Section response = sectionService.updateCapacitySection(sectionUpdateDTO, accountSupervisor);
        });;

    }

    @Test
    void testUpdateSectionIfDoesNotExistSupervisor (){
        //arrange
        when(supervisorRepository.findById(accountSupervisor.getId())).thenReturn(Optional.empty());
        when(sectionRepository.findById(section.getCode())).thenReturn(Optional.of(section));
        when(sectionRepository.save(any(Section.class))).thenReturn(section);


        //assert
        assertThrows(NotFoundException.class, () -> {
            Section response = sectionService.updateCapacitySection(sectionUpdateDTO, accountSupervisor);
        });;

    }

}
