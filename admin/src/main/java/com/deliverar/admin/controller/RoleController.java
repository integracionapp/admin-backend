package com.deliverar.admin.controller;

import com.deliverar.admin.model.dto.User.RoleRequest;
import com.deliverar.admin.model.dto.User.RoleResponse;
import com.deliverar.admin.model.dto.User.RoleToUserForm;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.service.UserService.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;

    @PostMapping("/")
    @Hidden
    public ResponseEntity<RoleResponse> saveRole(@RequestBody RoleRequest role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles/").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/user")
    @Hidden
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

}
