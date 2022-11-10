package com.company.library_javaEE.exceptions;

import javax.servlet.ServletException;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 11:50 PM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class AuthorizationException extends ServletException {
    public AuthorizationException(String message) {
        super ( message );
    }
}
