package com.JWT_S3_Security.JWT_S3_Security_Study.JWT;


import com.JWT_S3_Security.JWT_S3_Security_Study.model.UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    @Value("${spring.JWT.key}")
    // token 발급 키
    private String jwtSecret;

    @Value("${spring.JWT.expirationMs}")
    // JWT 토큰의 만료 시간
    // 일정 시간이 지나면 토큰으 더 이상 유효하지 않게 되면 인증 및 권한 부여를 위해 사용 할 수 없다.
    private int jwtExpirationMs;

    //사용자의 인증 정보를 기반으로 JWT 토큰을 생성하는 메소드이다.
    public String generateJwtToken(Authentication authentication , boolean rememberMe){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        // 사용자의 인증 정보를 가져온다.


        Date issuedAt = new Date();
        // 토큰이 발급된 시간을 나타내는 Date
        Date expirationDate = new Date((new Date()).getTime() + jwtExpirationMs);
        // 토큰이 만료되는 시간을 나타내는 Date

        if(rememberMe){
            expirationDate = new Date((new Date()).getTime() + jwtExpirationMs * 28); // 일주일
        }

        return Jwts.builder()
                //Jwt르 생성하기 위한 빌더 객체를 생성
                .setSubject(userPrincipal.getUsername())
                // 토큰의 주제로 사용할 이름을 설정
                .setIssuedAt(issuedAt)
                // 토큰의 발급 시간을 설정
                .setExpiration(expirationDate)
                // 토큰의 만료 시간을 설정
                .signWith(SignatureAlgorithm.HS512 , jwtSecret)
                // 토큰의 서명을 추가한다. 해당 알고리즘과 key값으로 서명한다.
                .compact();

    }

    // 주어진 토큰으로 사용자의 이름을 추출하는 메소드
    public String getUserIDFromJwtToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
        // Jets.parser() : JWT를 파싱하기 위한 파서 객체
        // setSigningKey : 토큰의 서명을 검증하기 위해 사용되는 비밀 키
        // parseClaimsJws : 주어진 토큰을 파싱하여 서명을 검증하고 ,토큰의 내용을 나타내느 Claims 객체를 반환
        // getBody : 토큰의 내용을 나타내는 Claims 객체를 가져온다
        // getSubject : 객체에서 사용자의 이름을 추출하여 반환한다.

    }

    // 주어진 JWT 토큰의 유효성을 검사하는 메소드
    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(authToken);
        }catch(JwtException e){
            return false;
        }
        return true;
    }



}
