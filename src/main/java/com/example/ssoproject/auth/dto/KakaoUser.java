package com.example.ssoproject.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoUser {
    private Long id;
    private KakaoAccount kakaoAccount;

    @Getter
    @Setter
    public static class KakaoAccount{
        private String email;
        private String gender;
        private String name;
    }
}
