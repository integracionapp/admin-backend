package com.deliverar.admin.model.entity;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import static javax.persistence.FetchType.EAGER;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
