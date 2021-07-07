package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="account")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    private String userName;

    private String password;
    
    //TODO: need to populate table
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    private Role role;

}
