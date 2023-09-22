package com.JWT_S3_Security.JWT_S3_Security_Study.Controller;


import com.JWT_S3_Security.JWT_S3_Security_Study.payload.AbstractDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.Request.AuthRequestDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.Response.AuthResponseDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.service.IUserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity getLogin(@RequestBody AuthRequestDTO requestDTO){
        AuthResponseDTO res = userService.getLogin(requestDTO);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping("/create")
    private ResponseEntity getUserCreate(@RequestBody AuthRequestDTO requestDTO){
        AbstractDTO res = userService.getUserCreate(requestDTO);
        return ResponseEntity.ok().body(res);
    }

}
