package com.JWT_S3_Security.JWT_S3_Security_Study.JWT;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
// AuthenticationEntryPoint : Spring Security의 인증 진입점을 구현한 인터페이스
public class AuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    // 인증되지 않은 요청이 들어왔을 대 호출되며,
    // HttpServletResponse 를 통해 클라이언트에게 401 Unauthorized 응답을 보낸다.
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED , "Error : Unauthorized");
    }
}
