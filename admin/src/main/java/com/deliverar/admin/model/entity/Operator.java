package com.deliverar.admin.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private BigInteger dni;
    private String phone;
    private String email;
    private LocalDateTime birthDate;
    private LocalDateTime registerDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "operator_id")
    private List<Address> addresses;
}
