package com.JWT_S3_Security.JWT_S3_Security_Study.payload.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
// note 여러가지 ResponseDTO객체를 응답할 때 해당 클래스를 상속받으면 만들어진 날짜 , 업데이트 된 날짜를 자동으로 ResponseDTO 매개변수에 포함 시킬 수 있다.
public class AbstractResponseDTO {

    // note @JsonFormat : Jackson 라이브러리에서 제공하는 어노테이션으로 , JAVA 객체를 JSON 형식으로
    //      직렬 화 할때 날짜와 시간을 지정하는데 사용한다. 이 어노테이션을 사용하면 날짜 및 시간 값을 JSON 문자열로 변환 할 때
    //      원하는 형식으로 사용 할 수 있다.
    //      shape : 날짜 및 시간 값을 어떻게 표현할지를 지정한다. 이 속성은 'JsonForam Shape' 열거형을 사용하며
    //              주로 'String' 값을 사용하여 값을 문자열로 변환한다.
    //      patten : JSON으로 직렬화 할 때 사용할 날짜 및 시간 패턴을 지정한다. 패턴은 문자열로 지정되며
    //               날짜 및 시간 값을 표시할 떄 형식을 나타낸다.
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
}
