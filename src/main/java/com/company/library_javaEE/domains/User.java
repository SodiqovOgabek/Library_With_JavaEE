package com.company.library_javaEE.domains;

import com.company.library_javaEE.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:32 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    private String password;
    private String name;
    private String surname;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserStatus status=UserStatus.USER;
}
