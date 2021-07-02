package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Section {

    @Id
    private String code;

    @Column
    @NotEmpty
    @Size(min = 2, max = 45)
    private String name;

    private String category;

    private int capacity;

    @ManyToOne
    private Warehouse warehouse;
}
