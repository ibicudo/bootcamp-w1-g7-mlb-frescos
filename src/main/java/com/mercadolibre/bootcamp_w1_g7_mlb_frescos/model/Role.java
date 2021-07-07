package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

//TODO: need to populate table with roles
@Entity
@Table(name="role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String id;

    @OneToMany(mappedBy = "role")
    private List<Account> accounts;

}