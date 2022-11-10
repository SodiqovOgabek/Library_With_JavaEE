package com.company.library_javaEE.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:18 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;

}