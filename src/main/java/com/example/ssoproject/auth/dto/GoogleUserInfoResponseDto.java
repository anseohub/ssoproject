package com.example.ssoproject.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleUserInfoResponseDto {

    @JsonProperty("sub")
    private String sub;

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_verified")
    private String emailVerified;

    @JsonProperty("name")
    private String name;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("locale")
    private String locale;
}
