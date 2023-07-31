package com.JWT_S3_Security.JWT_S3_Security_Study.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "role_type")
    private String roleType;
}
