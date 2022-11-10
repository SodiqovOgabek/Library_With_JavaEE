package com.company.library_javaEE.dto.auth;

import com.company.library_javaEE.enums.UserStatus;
import lombok.*;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:17 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */

    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    @NoArgsConstructor
    public class UserDTO {
        private Long id;
        private String email;
        private String name;
        private String surname;
        private UserStatus status;
    }

