package com.mercadolibre.ingrid_bicudo.service.section;

import com.mercadolibre.ingrid_bicudo.dtos.SectionUpdateDTO;
import com.mercadolibre.ingrid_bicudo.model.Account;
import com.mercadolibre.ingrid_bicudo.model.Section;

public interface SectionService {
    Section updateCapacitySection(SectionUpdateDTO sectionUpdateDTO, Account account);
}
