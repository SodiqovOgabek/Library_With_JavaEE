package com.company.library_javaEE.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:45 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@Getter
@AllArgsConstructor
public enum Language {

    UZ("Uzbek"),
    RU("Russian"),
    EN("English");

    private final String value;
}
