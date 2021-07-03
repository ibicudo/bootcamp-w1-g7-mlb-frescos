package com.mercadolibre.bootcamp_w1_g7_mlb_frescos.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Supervisor Supervisor;

    private String userName;
    // TODO: encrypt that
    private String password;

    private String role;
}
