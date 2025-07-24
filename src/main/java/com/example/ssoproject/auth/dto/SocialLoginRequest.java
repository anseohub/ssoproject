package com.example.ssoproject.auth.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SocialLoginRequest {
    private String accessToken;
    private String provider;

}
