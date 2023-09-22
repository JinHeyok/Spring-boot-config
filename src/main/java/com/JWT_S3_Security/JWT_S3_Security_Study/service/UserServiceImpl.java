package com.JWT_S3_Security.JWT_S3_Security_Study.service;

import com.JWT_S3_Security.JWT_S3_Security_Study.JWT.JwtUtils;
import com.JWT_S3_Security.JWT_S3_Security_Study.enums.RoleType;
import com.JWT_S3_Security.JWT_S3_Security_Study.exception.BadRequestException;
import com.JWT_S3_Security.JWT_S3_Security_Study.model.Role;
import com.JWT_S3_Security.JWT_S3_Security_Study.model.User;
import com.JWT_S3_Security.JWT_S3_Security_Study.model.UserDetailsImpl;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.AbstractDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.MessageDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.Request.AuthRequestDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.Response.AuthResponseDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.repository.RoleRepository;
import com.JWT_S3_Security.JWT_S3_Security_Study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;


    public Authentication authenticate(String id, String pw) {
        User user = userRepository.findByUserId(id).orElseThrow(() -> new BadRequestException("User Id Not Found"));
        // note 클라이언트로부터 요청온 아이디로 사용자를 찾아낸다.
        //     해당 아이디가 존재하지 않을 시 BadRequestException을 던져준다.
        if(!passwordEncoder.matches(pw , user.getPassword())){
            throw new BadRequestException("Wrong Password");
        }
        // note 클라이언트로부터 요청온 비밀번호를 찾은 아이디와 비교한다.
        //      저장된 비밀번호와 일치하지 않을 경우 BadRequestException을 던져준다.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(id , pw));
        // note authenticationManager를 사용하여 주어진 아이디와 비밀번호로 사용자의 인증을 시도한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // note SecurityContextHolder를 사용하여 현재 실행중인 스레드의 보안 컨텍스트에 인증정보(authentication)을 설정한다.
        return authentication;
    }
    @Override
    public AuthResponseDTO getLogin(AuthRequestDTO requestDTO) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        // note 해당 로그인의 정보를 담을 객체를 생성해준다.
        Authentication authentication = authenticate(requestDTO.getId() , requestDTO.getPw());
        // note authenticate함수를 통해 사용자의 인증정보를 가져온다.
        if(requestDTO.getRememberMe() == null){
            requestDTO.setRememberMe(false);
        }
        // note 자동로그인 상태 자동로그인 값이 없을경우 false로 지정 (Default 30분)
        String jwt = jwtUtils.generateJwtToken(authentication,requestDTO.getRememberMe());
        // note jwt토큰을 발급한다.
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // note 객체에서 사용자의 상세정보를 가져온다.
        //      getPrincipal() 메소드는 사용자의 주체(Principal)를 반환한다.
        RoleType roleType = userDetails.getAuthorities().stream().findFirst().get().getAuthority().equals("ROLE_ADMIN") ?
                RoleType.ROLE_ADMIN : RoleType.ROLE_USER;
        // note 사용자의 권한정보를 확인하여 해당 권한에 맞는 값을 저장해준다.
        authResponseDTO.setMessage("Login Successfully!");
        authResponseDTO.setUserId(userDetails.getUsername());
        authResponseDTO.setJwt(jwt);
        authResponseDTO.setRoleType(roleType);
        return authResponseDTO;
    }

    @Override
    public AbstractDTO getUserCreate(AuthRequestDTO requestDTO) {
        User user = new User();
        user.setUserId(requestDTO.getId());
        user.setPassword(passwordEncoder.encode(requestDTO.getPw()));
        Role role = roleRepository.findByRoleType("ROLE_ADMIN");
        user.setRole(role);
        userRepository.save(user);
        return new MessageDTO("User Create Successfully");
    }


}
