package com.company.library_javaEE.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:39 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@AllArgsConstructor
@Getter
public enum Genre {
    CRIME ( "Crime" ),
    HORROR ( "Horror" ),
    CI_FI ( "Ci-fi" ),
    DRAMA ( "Drama" ),
    ROMANCE ( "Romance" ),
    ROMANCE_DRAMA ( "Romance Drama" ),
    TUTORIAL ( "Tutorial" ),
    FANTASY ( "Fantasy" );

    private final String key;

    public String getKey() {
        return key;
    }
}
