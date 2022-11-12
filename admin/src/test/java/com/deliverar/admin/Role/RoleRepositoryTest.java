package com.deliverar.admin.Role;

import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    Role role;
    String roleName;

    @BeforeEach
    void setUp() {
        roleName = "ROLE_ADMIN";
    }

    @Test
    void findByName() {
        role = roleRepository.findByName(roleName);
        assertThat(role.getName()).isEqualTo(roleName);
    }
}