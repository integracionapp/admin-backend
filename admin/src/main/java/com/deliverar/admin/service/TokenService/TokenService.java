package com.deliverar.admin.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface TokenService {

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
