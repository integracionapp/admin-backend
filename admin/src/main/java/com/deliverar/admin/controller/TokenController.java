package com.deliverar.admin.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.deliverar.admin.model.dto.ExceptionResponse;
import com.deliverar.admin.model.dto.User.JWTResponse;
import com.deliverar.admin.model.dto.User.UserResponse;
import com.deliverar.admin.model.entity.Role;
import com.deliverar.admin.model.entity.User;
import com.deliverar.admin.service.TokenService.TokenService;
import com.deliverar.admin.service.UserService.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Tag(name = "Token API", description = "Token API developed by Deliverar Administrator")
public class TokenController {

    private final TokenService tokenService;

    @GetMapping("/refresh")
    @Operation(summary = "Refresh access token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Refresh token", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JWTResponse.class))),
            @ApiResponse(responseCode = "404", description = "Access Token has expired", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public void refreshToken(
            HttpServletRequest request, HttpServletResponse response,
            @RequestHeader("Authorization")
            @Parameter(name = "Authorization", example = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWxpYW4iLCJyb2xlcyI6WyJST0xFX09QRVJBVE9SIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6NTAwMC9sb2dpbiIsImV4cCI6MTY2Nzc2MjIyOH0.McoxGmXiFC7_zuGaQ1scQUhWokKlNAsahGpJ5km2Cv8")
            String token) throws IOException {
        tokenService.refreshToken(request,response);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @Operation(summary = "Request Access Token", description = "Parameters as a X-WWW-FORM-URLENCODED")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Access token", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JWTResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public void accessToken(
            @RequestParam("username") String username,
            @RequestParam("password") String password){
    }


}
