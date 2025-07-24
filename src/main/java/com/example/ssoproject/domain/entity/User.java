package com.example.ssoproject.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socialId;

    private String email;

    private String name;

    private String provider;

    @Builder.Default
    private String role = "ROLE_ADMIN";  //

    @CreationTimestamp
    private LocalDateTime createdAt;
}
