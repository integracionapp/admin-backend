package com.deliverar.admin.initializer;

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
        userService.saveRole(new Role(null, "ROLE_ADMIN"));
        userService.saveRole(new Role(null, "ROLE_PROVIDER"));
        userService.saveRole(new Role(null, "ROLE_FRANCHISE"));
        userService.saveRole(new Role(null, "ROLE_OPERATOR"));

        userService.saveUser(new User(null, "Gonzalo Bari", "gnb", "1234", new ArrayList<>()));
        userService.saveUser(new User(null, "Franco Siciliano", "sicilian", "1234", new ArrayList<>()));


        userService.addRoleToUser("gnb", "ROLE_ADMIN");
        userService.addRoleToUser("sicilian", "ROLE_PROVIDER");
    }
}
