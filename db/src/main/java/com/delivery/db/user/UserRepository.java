package com.delivery.db.user;

import com.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * SELECT *
     * FROM USER
     * WHERE id = ? AND status = ?
     * ORDER BY id desc
     * LIMIT 1
     */
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus status);

    /**
     * 로그인 시 사용할 조회 메서드
     * SELECT *
     * FROM USER
     * WHERE email = ? and password = ? and status = ?
     * ORDER BY id desc
     * LIMIT 1
     */
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
