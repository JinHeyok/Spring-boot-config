package com.JWT_S3_Security.JWT_S3_Security_Study.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestContoller {


    @GetMapping("/all")
    public ResponseEntity getTestAll(){
        return ResponseEntity.ok().body("test");
    }
}
