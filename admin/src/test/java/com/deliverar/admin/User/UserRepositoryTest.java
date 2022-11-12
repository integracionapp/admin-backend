package com.deliverar.admin.User;

import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    User user;
    String name;
    @BeforeEach
    void setUp() {
        name = "gnb";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUsername() {
        user = userRepository.findByUsername(name);
        assertThat(user.getUsername()).isEqualTo(name);
    }
}