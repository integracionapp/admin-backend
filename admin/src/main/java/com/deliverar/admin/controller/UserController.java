package com.deliverar.admin.controller;

import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.service.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/")
    public ResponseEntity<UserResponse>saveUser(@RequestBody UserRequest user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @GetMapping("/{username}")
    public Boolean isUsernameAvailable(@PathVariable String username){
        return userService.isUsernameAvailable(username);
    }

    @GetMapping("/credentials")
    public UserRequest userCredentials(){
        return userService.createUserCredential("ROLE_PROVIDER");
    }

    @GetMapping("/operators/login")
    public ResponseEntity<OperatorResponse> loginOperator(@RequestHeader("Authorization") String token) throws Exception {
        return new ResponseEntity<>(userService.loginOperator(token), HttpStatus.OK);
    }
}
