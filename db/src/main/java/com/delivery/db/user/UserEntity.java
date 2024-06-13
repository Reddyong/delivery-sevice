package com.delivery.db.user;

import com.delivery.db.BaseEntity;
import com.delivery.db.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder   // 부모로부터 상속받은 변수도 포함시키겠다는 어노테이션
public class UserEntity extends BaseEntity {

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    // columnDefinition 으로 DB 의 테이블의 속성과 매칭시켜줌 -> 안넣어주면, enum 과 varchar 를 매칭 못시켜줌
    @Column(length = 50, nullable = false, columnDefinition = "varchar(50)")
    @Enumerated(value = EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;
    private LocalDateTime lastLoginAt;
}
