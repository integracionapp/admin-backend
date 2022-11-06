package com.deliverar.admin.controller;

import com.deliverar.admin.model.dto.ExceptionResponse;
import com.deliverar.admin.model.dto.Franchise.FranchiseResponse;
import com.deliverar.admin.model.dto.Operator.OperatorResponse;
import com.deliverar.admin.model.dto.User.UserRequest;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.service.UserService.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User API", description = "User API developed by Deliverar Administrator")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @Operation(summary = "Get all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<List<UserResponse>>getUsers(
            @RequestHeader("Authorization")
            @Parameter(name = "Authorization", example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpYW4iLCJyb2xlcyI6WyJST0xFX09QRVJBVE9SIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAwMC9sb2dpbiIsImV4cCI6MTY2Nzc2MjIyOH0.McoxGmXiFC7_zuGaQ1scQUhWokKlNAsahGpJ5km2Cv8")
            String token) {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/")
    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<UserResponse>saveUser(
            @RequestHeader("Authorization")
            @Parameter(name = "Authorization", example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpYW4iLCJyb2xlcyI6WyJST0xFX09QRVJBVE9SIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAwMC9sb2dpbiIsImV4cCI6MTY2Nzc2MjIyOH0.McoxGmXiFC7_zuGaQ1scQUhWokKlNAsahGpJ5km2Cv8")
            String token,
            @RequestBody UserRequest user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @Hidden
    @GetMapping("/{username}")
    public Boolean isUsernameAvailable(@PathVariable String username){
        return userService.isUsernameAvailable(username);
    }

    @Hidden
    @GetMapping("/credentials")
    public UserRequest userCredentials(){
        return userService.createUserCredential("ROLE_PROVIDER");
    }

    @GetMapping("/operators/login")
    @Operation(summary = "Login for the operators")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Operator found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperatorResponse.class))),
            @ApiResponse(responseCode = "404", description = "User not found with user id -> x", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ResponseEntity<OperatorResponse> loginOperator(
            @RequestHeader("Authorization")
            @Parameter(name = "Authorization", example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpYW4iLCJyb2xlcyI6WyJST0xFX09QRVJBVE9SIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAwMC9sb2dpbiIsImV4cCI6MTY2Nzc2MjIyOH0.McoxGmXiFC7_zuGaQ1scQUhWokKlNAsahGpJ5km2Cv8")
            String token) throws Exception {
        return new ResponseEntity<>(userService.loginOperator(token), HttpStatus.OK);
    }
}
