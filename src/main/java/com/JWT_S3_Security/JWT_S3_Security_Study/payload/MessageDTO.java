package com.JWT_S3_Security.JWT_S3_Security_Study.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO extends AbstractDTO {
    private String message;
}
