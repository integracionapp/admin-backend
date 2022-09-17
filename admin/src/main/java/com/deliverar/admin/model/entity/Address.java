package com.deliverar.admin.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer number;
    private String latitude;
    private String longitude;
    private String zipCode;
    private String city;
    private String province;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;
}
