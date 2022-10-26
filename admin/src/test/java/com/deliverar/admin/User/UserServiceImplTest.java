package com.deliverar.admin.User;

import com.deliverar.admin.mappers.UserMapper;
import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.repository.RoleRepository;
import com.deliverar.admin.repository.UserRepository;
import com.deliverar.admin.service.UserService.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private final UserMapper userMapper = UserMapper.INSTANCE;

    UserRequest userRequest;
    RoleRequest roleRequest;

    RoleResponse roleResponse;
    UserResponse userResponse;

    @BeforeEach
    void setUp() {
        roleRequest = RoleRequest.builder()
                .name("ROLE_EXAMPLE")
                .build();
        userRequest = UserRequest.builder()
                .name("Juan Iviglia")
                .username("pampa")
                .password("1234")
                .roles(new ArrayList<>())
                .build();

        roleResponse = userService.saveRole(roleRequest);
        userResponse = userService.saveUser(userRequest);

    }

    @AfterEach
    void tearDown() {
        userRepository.delete(userRepository.findByUsername("pampa"));
        roleRepository.delete(roleRepository.findByName("ROLE_EXAMPLE"));
    }

    @Test
    void saveUser() {
        UserResponse userFound = userMapper.userToUserResponse(userRepository.findByUsername(userRequest.getUsername()));
        assertThat(userFound).usingRecursiveComparison().isEqualTo(userResponse);

    }

    @Test
    void saveRole() {
        RoleResponse roleFound = userMapper.roleToRoleResponse(roleRepository.findByName(roleRequest.getName()));
        assertThat(roleResponse).usingRecursiveComparison().isEqualTo(roleFound);
    }

    @Test
    void addRoleToUser() {
        userService.addRoleToUser(userRequest.getUsername(),roleRequest.getName());
        UserResponse userResponse = userMapper.userToUserResponse(userRepository.findByUsername(userRequest.getUsername()));
        assertThat(userResponse.getRoles()).isNotEmpty();
        int x = 0;
        for (RoleResponse r: userResponse.getRoles()){
            if (r.getName().equalsIgnoreCase(roleRequest.getName())){
                assertThat(r.getName()).isEqualTo(roleRequest.getName());
                x = 1;
            }
        }
        assertThat(x).isEqualTo(1);
    }

    @Test
    void getUser() {
        User user = userService.getUser(userRequest.getUsername());
        assertThat(user.getUsername()).isEqualTo(userRequest.getUsername());
    }

    @Test
    void getUsers() {
        List<UserResponse> usersS = userService.getUsers();
        List<UserResponse> usersR = userMapper.userToUserResponse(userRepository.findAll());
        int x = 0;
        for(UserResponse u: usersS){
            assertThat(u.getId()).isEqualTo(usersR.get(x).getId());
            x++;
        }
    }
}