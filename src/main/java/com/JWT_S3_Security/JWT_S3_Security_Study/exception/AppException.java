package com.JWT_S3_Security.JWT_S3_Security_Study.exception;


//note RuntimeException
//      Java에서 발생하는 실행 시 예외를 나타내는 클래스이디ㅏ.
//      Exception 클래스의 하위 클래스이며, 일반적으로 컴파일러가 체크하지 않는 예외들을 포함한다.
public class AppException extends RuntimeException {

    // note AppException은 하나의 생성자를 가지고 있으며 생성자는 문자열 타입의 매개변수를 받는다.
    //      super(message)sms 부모 클래스인 RuntimeException의 생성자를 호출하여 예외 메시지를 설정한다.
    public AppException(String message) {
        super(message);
    }

}
