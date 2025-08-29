package com.Indrajit.ecommerce_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, length = 512)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "user_role")
    private String role;
}
