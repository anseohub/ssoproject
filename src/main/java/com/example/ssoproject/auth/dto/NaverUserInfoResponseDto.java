package com.example.ssoproject.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverUserInfoResponseDto {
    private NaverUser response; // JSON 응답에서 "response" 필드 안에 유저 정보 있음
}
