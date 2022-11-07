package com.company.library_javaEE.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:59 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@AllArgsConstructor
@Getter
public enum UserStatus {
    USER ( 50 ),
    ADMIN ( 75 ),
    SUPER_ADMIN ( 100 );
    private final Integer priority;

}
