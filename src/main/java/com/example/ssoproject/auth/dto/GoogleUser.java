package com.example.ssoproject.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleUser {
    private String id;
    private String email;
    private String name;
}
