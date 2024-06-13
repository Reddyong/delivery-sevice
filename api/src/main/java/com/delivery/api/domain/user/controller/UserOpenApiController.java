package com.delivery.api.domain.user.controller;

import com.delivery.api.common.api.Api;
import com.delivery.api.domain.user.business.UserBusiness;
import com.delivery.api.domain.user.controller.model.UserLoginRequest;
import com.delivery.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.api.domain.user.controller.model.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/open-api/user")
public class UserOpenApiController {
    /**
     * 로그인 안해도 공개된 컨트롤러
     */

    private final UserBusiness userBusiness;

    // 사용자 가입 요청
    @PostMapping(path = "/register")
    public Api<UserResponse> register(
            @Valid
            @RequestBody Api<UserRegisterRequest> request
    ) {
        var response = userBusiness.register(request.getBody());

        return Api.OK(response);
    }

    @PostMapping(path = "/login")
    public Api<UserResponse> login(
            @Valid
            @RequestBody Api<UserLoginRequest> request
    ) {
        var response = userBusiness.login(request.getBody());

        return Api.OK(response);
    }
}
