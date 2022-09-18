package com.deliverar.admin.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Provider {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;
    private BigInteger cuit;
    private String phone;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "provider_id")
    private List<Address> address;
    private String webPageUrl;



}
