package com.company.library_javaEE.dto.auth;

import lombok.*;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:17 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserCreateDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
}