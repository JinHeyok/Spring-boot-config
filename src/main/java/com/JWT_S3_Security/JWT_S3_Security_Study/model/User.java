package com.JWT_S3_Security.JWT_S3_Security_Study.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "user")
public class User extends Base{
    //base 부모로 지정하여 자동으로 만들 날짜 , 업데이트 날짜를 생성

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private long idx;

    @Column(name = "user_id" , nullable = false)
    private String userId;

    @Column(name = "user_password" , nullable = false)
    private String password;

    @JoinColumn(name = "role_id" , nullable = false)
    @ManyToOne
    private Role role;

}
