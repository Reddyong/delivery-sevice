package com.delivery.api.account;

import com.delivery.api.account.model.AccountMeResponse;
import com.delivery.db.account.AccountEntity;
import com.delivery.db.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping(path = "/me")
    public AccountMeResponse me() {
        return AccountMeResponse.builder()
                .name("홍길동")
                .email("A@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();
    }
}
