package com.JWT_S3_Security.JWT_S3_Security_Study.Controller;


import com.JWT_S3_Security.JWT_S3_Security_Study.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity getLogin(){
        System.out.println("auth Login API");


      return ResponseEntity.ok().body("test");
    }


}
