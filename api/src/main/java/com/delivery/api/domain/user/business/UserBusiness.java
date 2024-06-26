package com.delivery.api.domain.user.business;

import com.delivery.api.common.annotation.Business;
import com.delivery.api.common.error.ErrorCode;
import com.delivery.api.common.exception.ApiException;
import com.delivery.api.domain.user.controller.model.UserLoginRequest;
import com.delivery.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.api.domain.user.controller.model.UserResponse;
import com.delivery.api.domain.user.converter.UserConverter;
import com.delivery.api.domain.user.service.UserService;
import com.delivery.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;

    /**
     * 사용자에 대한 가입 처리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. saved entity -> response
     * 4. response return
     */
    public UserResponse register(UserRegisterRequest request) {

        var userEntity = userConverter.toEntity(request);
        var registeredUser = userService.register(userEntity);

        return userConverter.toResponse(registeredUser);

//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));
    }

    /**
     * 1. email, password 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성
     * 4. token response
     */
    public UserResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());
        // 사용자 없으면 throw

        // TODO : 토큰 생성 로직으로 변경하기

        return userConverter.toResponse(userEntity);
    }
}
