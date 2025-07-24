package com.example.ssoproject.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverUser {
    private Response response;
    @Getter
    @Setter
    public static class Response{
        private String id;
        private String email;
        private String name;
        private String gender;
        private String age;
    }
}
