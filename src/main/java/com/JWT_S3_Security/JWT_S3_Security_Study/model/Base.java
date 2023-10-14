package com.JWT_S3_Security.JWT_S3_Security_Study.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
// note  JPA에서 상속관계를 맵핑할 떄 사용한다.
// note  Base 클래스를 상속반든 하위 엔티티 클래스들은 Base 클래스의 필드를 상속받고 데이터베이스 테이블과 맵핑 될 수 있다.
@DynamicUpdate
// note  Hibernate 에서 제공하는 어노테이션으로 엔티티의 변경된 필드만을 업데이트 하는 동적 업데이트 쿼리를 생성한다.
// note  업데이트 성능을 향상 시킬 수 있다.
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// note  상속 전략을 사용하기 설정하기 위해 사용한다.
// note  해당 전략은 각 엔티티 클래스마다 별도의 테이블을 생성하는 전략이다.
public abstract class Base {

    // note Create Date Time
    @Column(name = "createAt" , nullable = false , updatable = false)
    // note  null이 안되도록 , 업데이트가 안되도록
    private LocalDateTime createAt;

    // note Update Date Time
    @Column(name = "updateAt" , nullable = false)
    private LocalDateTime updateAt;

    @PrePersist
    // note  JPA에 생명주기 메소드
    // note  적용된 메소드는 엔티티가 영속화 되기 전에 호출된다.
    protected  void prePersist(){
        if(this.createAt == null) this.createAt = LocalDateTime.now();
        if(this.updateAt == null) this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    // note  JPA에 생명주기 메소드
    // note  적영된 메소드는 엔티티가 업데이트 됙전에 호출된다.
    protected void preUpdate(){this.updateAt = LocalDateTime.now();
    }

    // note @PrePersist, @Preupdate
    // note  필드를 자동으로 설정할 수 있게 만들어준다.

}
