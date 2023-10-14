package com.JWT_S3_Security.JWT_S3_Security_Study.payload.Response;

import com.JWT_S3_Security.JWT_S3_Security_Study.payload.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "List Response" , description = "List Response Payload")
// note 제네릭 클래스로 <T>는 타입의 매개변수로 이 클래스의 인스턴스가 어떤 타입 <T>에 대해 생성될 수 있는지를 의미
//      extends AbstractDTO 로부터 속성 및 동작을 상솓받음 , 모든 객체의 return 값을 AbstractDTO 타입으로 지정할 수 있음
public class ListResponseDTO<T> extends AbstractDTO {

    // note ListResponseDTO 로 return 받을 때 JSON으로 직렬화 될 떄 "list"로 표현 Json 데이터의 키값이 됨
    @JsonProperty("list")
    // note 모든 데이터 타입을 저장 할 수 있는 매개변수를 생성, 응답 데이터를 저장하는 사용
    private List<T> listResponseDTO;

    // note 객체 목록을 매개변수로 받아 이를 'ListReponseDTO' 필드에 할당하는 비공개 생성자 , 인스턴스를 생성하는데 사용된다.
    private ListResponseDTO(List<T> listObject) { this.listResponseDTO = listObject;}

    // note 주어진 매개변수로 ListResponseDTO의 인스턴스를 생성하고 반환하는 팩토링 메서드
    private static <T> ListResponseDTO<T> of(List<T> listObject) {return new ListResponseDTO<>(listObject);}
}

// note 클래스는 제네릭 <T>의 객체목록을 API에 응답에서 반환하기 위해 제네릭 응답DTO를 생성하는 데 사용한다.
//      JSON직렬화와 함꼐 사용하도록 설계되어 있으므르 API엔드포인트에서 데이터 목록을 반환하는데 사용할 수 있다.