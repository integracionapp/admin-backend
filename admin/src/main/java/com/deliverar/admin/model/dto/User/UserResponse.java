package com.deliverar.admin.model.dto.User;

import com.deliverar.admin.model.entity.Role;
import lombok.*;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserResponse {

    private Long id;
    private String name;
    private String username;
    private Collection<RoleResponse> roles;
}
