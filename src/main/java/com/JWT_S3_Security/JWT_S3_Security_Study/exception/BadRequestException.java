package com.JWT_S3_Security.JWT_S3_Security_Study.exception;

// note (Custom Exception)
//  AppException에 클래스의 모든 속성과 메소드를 상속받는다.
//  BadRequestException은 하나의 생성자를 가지고 있는다.
//  정의된 BadRequestException 클래스는 애플리케이션에서 클라이언트의 잘못된 요청이 있을 때
//  해당 예외를 던질 수 있다.
public class BadRequestException extends AppException {

    public BadRequestException(String message) {
        super(message);
    }

}
