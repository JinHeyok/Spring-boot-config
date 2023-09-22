package com.JWT_S3_Security.JWT_S3_Security_Study;

import com.JWT_S3_Security.JWT_S3_Security_Study.JWT.AuthEntryPoint;
import com.JWT_S3_Security.JWT_S3_Security_Study.JWT.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
// 해당 클래스가 스프링의 설정 클래스임을 나타낸다.
@EnableWebSecurity
// 웹 보안을 기능을 활성화 한다.
@EnableMethodSecurity(prePostEnabled = true)
// 메소드 수준의 보안을 활성화 한다.
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    // 사용자 세부 정보 서비스에 대한 인스턴스를 주입받는다. 이 서비스는 사용자의 세부 정보를 로드하는 역할을 한다.

    @Autowired
    private AuthTokenFilter authTokenFilter;
    // 인증 토큰 필터를 처리하기 위한 AuthTokenFilter 객체ㅇ

    @Autowired
    private AuthEntryPoint authEntryPoint;
    // 인증되지 않은 요청에 대한 처리를 담당한다.


    @Bean
    // DAO 인증 프로바이더에 대한 빈을 정의한다.
    // 이 프로바이더는 비밀번호 인코더와 사용자 세부 정보 서비스를 설정하여 사용자를 인증한다.
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        // 비밀번호의 인코더를 설정한다. passwordEncoder()를 호출하여 비밀번호 인코더 빈을 가져옵니다.
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        // 사용자의 세부 정보 서비스를 설정한다.
        // userDetailsService는 사용자의 세부 정보를 로드하는 역할을 한다.
        return daoAuthenticationProvider;
    }

    @Bean
    // 비밀번호 인코더에 대한 빈을 정의한다.
    // 이 빈은 비밀번호를 인코딩하고 검증하는데 사용된다.
    // 비밀번호를 안전하게 저장하기 위해 사용된다.
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
        // 스프링 시큐리티에서 제공하는 비밀번호 인코더중 하나이다.
        // BCcypt 해서 알고리즘을 사용하여 비밀번호를 암호화한다.
        // 이 알고리즘은 안전한 해시 함수로 알려져 있으며, 해시된 비밀번호는 복호화 할 수 없다.
    }


    @Bean
    // 인증 매니저에 대한 빈을 정의한다.
    // 인증 매니저는 사용자의 인증을 처리하는 역할을 한다.
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
        // AuthenticationConfiguration : 스프링 시큐리티에서 제공하는 인증 구성 객체
        // getAuthenticationManager()로 인증 매니저를 가져온다.
    }


    @Bean
    // 보안 필터 체인에 대한 빈을 정의한다.
    // httpSecurity 객체를 사용하여 보안 규칙을 설정한다.
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .cors().and().csrf().disable()
                // cors().and().csrf().disable() :CORS와 CSRF 보호를 비활성화 한다.
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
                // 인증 진입 지점을 설정한다.
                // 인가되지 않은 접근 시도가 발생할 경우 unauthhorizeHandler를 사용하요 처리한다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 세션 관리 정책을 설정한다. 상태를 유지하지않는 세션을 사용한다.
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/api/docs/**").permitAll()
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .anyRequest().authenticated()
                );
                //요청에 대한 접근 규칙을 설정한다.
                //authorizeRequests 객체를 사용하여 다양한 URL에 대한 접근 규칙을 지정한다.
                // 제공된 코드에서는 특정 URL에 대한 공개 접근을 허용하고, 그 외의 모든 요청에 대해 인증이 필요하다.
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
