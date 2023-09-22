package com.JWT_S3_Security.JWT_S3_Security_Study.payload.Request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "authRequestDTO", description = "Auth Request DTO")
public class AuthRequestDTO {
    @Schema(name = "id", description = "Auth Id", example = "admin")
    private String id;

    @Schema(name = "pw", description = "Auth Pw", example = "admin")
    private String pw;

    @Schema(name = "rememberMe" ,description = "Remember Me" , example = "false")
    private Boolean rememberMe;
}
