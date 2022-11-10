package com.company.library_javaEE.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:18 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@AllArgsConstructor
@Data
public class UserLoginDTO {
    private String email;
    private String password;
}