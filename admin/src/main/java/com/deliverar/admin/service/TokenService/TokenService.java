package com.deliverar.admin.service.TokenService;

import com.auth0.jwt.algorithms.Algorithm;
import com.deliverar.admin.model.dto.User.JWTResponse;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    JWTResponse generateAccessAndRefreshToken(User user, HttpServletRequest request, HttpServletResponse response);

    Algorithm getAlgorithm();
}
