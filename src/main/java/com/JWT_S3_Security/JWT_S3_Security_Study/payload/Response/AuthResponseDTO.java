package com.JWT_S3_Security.JWT_S3_Security_Study.payload.Response;


import com.JWT_S3_Security.JWT_S3_Security_Study.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "authResponseDTO", description = "Auth Response DTO")
public class AuthResponseDTO {

    @Schema(name = "message", description = "Message", example = "message")
    private String message;
    @Schema(name = "userId", description = "Auth User ID", example = "admin")
    private String userId;
    @Schema(name = "jwt", description = "jwt", example = "token")
    private String jwt;
    @Schema(name = "roleType", description = "roleType", example = "roleType")
    private RoleType roleType;
}
