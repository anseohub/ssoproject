package com.example.ssoproject.auth.service;

import com.example.ssoproject.domain.entity.User;
import com.example.ssoproject.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void processOAuthPostLogin(OAuth2User oAuth2User, String provider) {

        System.out.println("Provider: " + provider);
        System.out.println("Attributes: " + oAuth2User.getAttributes());

        String socialId = extractSocialId(oAuth2User, provider);
        if (socialId == null) {
            throw new RuntimeException("소셜 ID를 가져올 수 없습니다.");
        }

        userRepository.findBySocialIdAndProvider(socialId, provider)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setSocialId(socialId);
                    newUser.setProvider(provider);
                    newUser.setName(extractName(oAuth2User, provider));
                    newUser.setEmail(extractEmail(oAuth2User, provider));
                    newUser.setRole("USER");
                    newUser.setCreatedAt(LocalDateTime.now());
                    return userRepository.save(newUser);
                });

        System.out.println("로그인 성공!");
    }

    public String extractSocialId(OAuth2User oAuth2User, String provider) {
        Object id = oAuth2User.getAttributes().get("id");
        if (id == null) {
            throw new IllegalArgumentException("소셜 ID가 없습니다: provider=" + provider);
        }
        return id.toString();
    }


    public String extractName(OAuth2User oAuth2User, String provider) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        switch (provider.toLowerCase()) {
            case "google":
                return attributes.get("name").toString();

            case "naver":
                return attributes.get("name").toString();

            case "kakao":
                //null  (닉네임 없으면 "Unknown")
                Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
                Map<String, Object> profile = kakaoAccount != null ? (Map<String, Object>) kakaoAccount.get("profile") : null;
                return profile != null && profile.get("nickname") != null
                        ? profile.get("nickname").toString()
                        : "Unknown";


            default:
                return "Unknown";
        }
    }

    public String extractEmail(OAuth2User oAuth2User, String provider) {
        Map<String, Object> attributes = oAuth2User.getAttributes();

        switch (provider.toLowerCase()) {
            case "google":
                return attributes.get("email").toString();

            case "naver":
                return attributes.get("email").toString();

            case "kakao":
                // email null이어도 저장되게 "no_email" 반환
                Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
                return kakaoAccount != null && kakaoAccount.get("email") != null
                        ? kakaoAccount.get("email").toString()
                        : "no_email";
            default:
                return "no_email";
        }
    }
}
