package com.company.library_javaEE.exceptions;

import javax.servlet.ServletException;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:17 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class NotFoundException extends ServletException {

    public NotFoundException(String message){
        super(message);
    }
}
