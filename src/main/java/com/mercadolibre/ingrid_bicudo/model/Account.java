package com.mercadolibre.ingrid_bicudo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

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
    
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id", nullable = false)
    private Role role;
}
