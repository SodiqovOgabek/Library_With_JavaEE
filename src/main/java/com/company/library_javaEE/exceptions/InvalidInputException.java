package com.company.library_javaEE.exceptions;

import javax.servlet.ServletException;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 11:49 PM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class InvalidInputException extends ServletException {
    public InvalidInputException(String message){
        super(message);
    }
}
