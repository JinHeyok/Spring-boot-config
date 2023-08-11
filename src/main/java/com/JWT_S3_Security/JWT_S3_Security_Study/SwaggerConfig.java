package com.JWT_S3_Security.JWT_S3_Security_Study;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// note
//  Configuration 클래스가 스프링의 구성 클래스임을 나태난다.
@Configuration
public class SwaggerConfig {

    // note
    //     Bean : 스프링 컨텍스트에 빈으로 등록된 객체를 생성하는 메소드를 나타낸다. (OpenAPI 객체를 반환한다.)
    //     OpenAPI : Swagger 문서를 정의하는 클래스 OpenAPI객체를 생성해하여
    //     Swagger의 기본 설정을 구성할 수 있다.
    @Bean
    public OpenAPI openAPI() {
        // note 보안 스키마의 이름을 'bearerAuth'로 설정한다.
        final String securitySchemeName = "bearerAuth";
        // note
        //      API 문서에 대한 정보를 설정하는 메소드
        //      info객체를 생성하여 API 문서의 제목 , 설명 , 버전 등을 설정한다.
        //      .version : 버전을 설정한다.
        //      .title : 문서 제목을 설정한다.
        //      .description : 문서 디스크립션을 설정한다.
        Info info = new Info()
                .version("1.0 Version")
                .title("Swagger API Document 문서 제목")
                .description("Swagger API document 문서 디스크립션 <br>" +
                        "1.0 : Swagger Config 최초 설정");


        // note
        //      OpenAPI 객체를 생성하여 Swagger 문서를 정의한다.
        //      .addSecurityItem() : 보안 요구사항을 추가하는 메소드 여기서 bearerAuth라는 보안 요구사항을 추가하고 있다.
        //      .components : 객체를 생성하여 보안 스키마를 구성한다.
        //      .addSecuritySchemes : 보안 스키마를 추가한다. 여기서는 BearerAuth라는 보안 스키마를 추가한다.
        //      SecurityScheme 객체를 생성하여 bearerAUth 라는 보안 스키마를 추가
        //          .name : bearerAuth라는 보안 스키마의 이름을 나타낸다
        //                  일반저그로 사용자가 지정한 식별자를 사용하며 Swagger 문서에 대한 보안 스키마를 참조할 떄 사용한다.
        //          .type : 보안 스키마의 타입을 나타낸다. SecurityScheme.Type 열거형을 통해 다양한 타입을 선택할 수 있다.
        //                  여기서는 HTTP타입을 사용한다. HTTP타입은 HTTP 요청 헤더나 쿼리 매개변수를 사용하여 인증을 처맇나느 경우 사용한다.
        //          .bearerFormat : 베어러 토큰 포맷을 나태난다. 베어러 토큰은 OAuth2.0 인증 프로 토콜에서 사용되는 토큰 유형 중 하나이다.
        //                          bearerFormat은 토큰의 형식을 지정하는데 사욜한다. 에를 들어 JWT(JSON Web Token)을 사용하는 경우 bearerFormat을 "JWT"로 설정 할 수 있다.
        return new OpenAPI()
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new io.swagger.v3.oas.models.security.SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(info);


    }

}
