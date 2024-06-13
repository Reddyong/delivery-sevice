package com.delivery.api.domain.user.controller;

import com.delivery.api.domain.user.business.UserBusiness;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/user")
public class UserApiController {
    /**
     * 로그인을 해야만 쓸 수 있는 컨트롤러
     */

    private final UserBusiness userBusiness;
}
