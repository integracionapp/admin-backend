package com.deliverar.admin.initializer;

import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.repository.UserRepository;
import com.deliverar.admin.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component @Slf4j @RequiredArgsConstructor
public class UserDataLoader implements ApplicationRunner {

    private final UserService userService;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.saveRole(new RoleRequest("ROLE_ADMIN"));
        userService.saveRole(new RoleRequest( "ROLE_PROVIDER"));
        userService.saveRole(new RoleRequest( "ROLE_FRANCHISE"));
        userService.saveRole(new RoleRequest( "ROLE_OPERATOR"));

        userService.saveUser(new UserRequest("Gonzalo Bari", "gnb", "1234", new ArrayList<>()));
        userService.saveUser(new UserRequest( "Franco Siciliano", "sicilian", "1234", new ArrayList<>()));


        userService.addRoleToUser("gnb", "ROLE_ADMIN");
        userService.addRoleToUser("sicilian", "ROLE_PROVIDER");
    }
}
