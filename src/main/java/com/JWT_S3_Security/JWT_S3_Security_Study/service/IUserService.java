package com.JWT_S3_Security.JWT_S3_Security_Study.service;

import com.JWT_S3_Security.JWT_S3_Security_Study.payload.AbstractDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.Request.AuthRequestDTO;
import com.JWT_S3_Security.JWT_S3_Security_Study.payload.Response.AuthResponseDTO;

public interface IUserService {


    AuthResponseDTO getLogin(AuthRequestDTO requestDTO);

    AbstractDTO getUserCreate(AuthRequestDTO requestDTO);
}
