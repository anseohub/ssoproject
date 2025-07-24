package com.example.ssoproject.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookUser {
    private String id;
    private String name;
    private String email;
}
