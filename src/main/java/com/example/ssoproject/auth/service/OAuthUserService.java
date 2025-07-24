package com.example.ssoproject.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthUserService extends DefaultOAuth2UserService {

    private final AuthService authService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId();

        Map<String, Object> attributes = oAuth2User.getAttributes();

        if ("naver".equals(provider)) {
            Map<String, Object> response = (Map<String, Object>) attributes.get("response");
            attributes = Map.of(
                    "id", response.get("id"),
                    "name", response.get("name"),
                    "email", response.get("email")
            );

        } else if ("kakao".equals(provider)) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            attributes = Map.of(
                    "id", attributes.get("id"),
                    "name", profile != null ? profile.get("nickname") : "사용자"
                    //"email", kakaoAccount != null ? kakaoAccount.get("email") : null
            );

        } else if ("google".equals(provider)) {
            attributes = Map.of(
                    "id", attributes.get("sub"),  // ✅ 구글 고유 ID는 sub
                    "name", attributes.get("name"),
                    "email", attributes.get("email")
            );
        }

        //
        authService.processOAuthPostLogin(new DefaultOAuth2User(
                Collections.singleton(oAuth2User.getAuthorities().iterator().next()),
                attributes,
                "id"
        ), provider);

        return new DefaultOAuth2User(
                Collections.singleton(oAuth2User.getAuthorities().iterator().next()),
                attributes,
                "id"
        );
    }
}
