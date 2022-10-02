package com.deliverar.admin.model.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Franchise {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String phone;
    private BigInteger cuit;
    private String webPageUrl;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "franchise_id")
    private List<Address> address;



}
