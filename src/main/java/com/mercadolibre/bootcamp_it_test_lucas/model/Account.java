package com.mercadolibre.bootcamp_it_test_lucas.model;

import java.util.UUID;

import javax.persistence.*;

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
