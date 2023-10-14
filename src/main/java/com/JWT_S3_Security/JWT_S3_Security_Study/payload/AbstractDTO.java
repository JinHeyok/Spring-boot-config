package com.JWT_S3_Security.JWT_S3_Security_Study.payload;

// note 추상클래스 정의
//      - 다수의 DTO 클래스들이 공통된 속성이나 메서드를 가질 경우,이러한 공통점을 추상 클래스에 정의하고 다른 DTO
//        클래스들이 이 클래스를 상속하여 재사용한다.
//      - 특정 작업을 수행하는 클래스 계층 구조에서 기본 동작을 재공하는 추상 클래스를 사용 될 수 있다.
//      - 인터페이스와 결합하여 인터페이스의 메서드 중 일부를 구현하는 추상클래스로 사용 될 수 있다.
//      * 'AbstractDTO' 클래스는 다른 클래스에게 공통된 특성을 제공하기 위한 기반 클래스로 사용된다.
//         추상 클래스 자체로는 객체를 생성할 수 없으며 , 서브 클래스에서 구체적인 동작을 정의하고 구현해야한다.
public abstract class AbstractDTO {
}
