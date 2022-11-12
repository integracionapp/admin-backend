package com.deliverar.admin.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Provider {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;
    private BigInteger cuit;
    private String phone;
    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "provider_id")
    private List<Address> address;
    private String webPageUrl;
}
