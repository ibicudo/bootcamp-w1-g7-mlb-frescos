package com.mercadolibre.ingrid_bicudo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supervisor {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    @NotEmpty
    @Size(min = 2, max = 45)
    private String name;

    @OneToOne()
    private Warehouse warehouse;
}
