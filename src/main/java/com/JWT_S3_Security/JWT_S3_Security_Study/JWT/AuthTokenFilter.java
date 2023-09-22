package com.JWT_S3_Security.JWT_S3_Security_Study.JWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// Component 어노테이션을 사용하여 컴포넌트를 등록된 필터 클래스입니다.
// OncePerRequestFilter 를 상속받아 한번의 요청에 대한 한 번만 실행되도록 구현
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    // JWT 토큰을 다루기 jwtUtils 객체이다.
    private JwtUtils jwtUtils;

    @Autowired
    // 사용자 세부 정보를 제공하기 위한 객체
    private UserDetailServiceImpl userDetailService;

    // 로깅을 위한 객체
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    @Override
    // 요청이 들어올 때마다 실행되는 메소드
    // 실제로 JWT 토큰을 처리하고 사용자 인증 정보를 설정하는 역할
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            // 헤더에 존재하는 JWT 토큰을 반환한다.

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // JWT 유효성 검사
                String userID = jwtUtils.getUserIDFromJwtToken(jwt);
                // 주어진 JWT 토큰으로 사용자의 이름을 추출하는 메소드

                UserDetails userDetails = userDetailService.loadUserByUsername(userID);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // Spring security의 인증객체 사용자 세부 정보, 인증된 비밀전호 및 권한 목록을 표시한다.

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // 인증 객체에 요청의 세부 정보를 추가한다.

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                // Spring seucrity의 사용자 인증 객체를 설정한다.
            }

        } catch (Exception e) {
            logger.error("설정된 유저 정보가 없습니다. : {} ", e);
        }
        filterChain.doFilter(request, response);
    }

    // 요청 헤더에 JWT 토큰을 추출하는 메소드입니다.
    // Authorization 헤더에서 Bearer 로 시작하는 형식으로 JWT 토큰을찾아 반환한다.
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
