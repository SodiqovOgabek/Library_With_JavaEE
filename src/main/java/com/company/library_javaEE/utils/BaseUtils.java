package com.company.library_javaEE.utils;

import com.company.library_javaEE.configs.PasswordConfigurer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/10/2022 12:49 AM (Thursday)
 * Library_JavaEE/IntelliJ IDEA
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseUtils {
    private static BaseUtils instance;

    public String encode(String rawPassword) {
        return PasswordConfigurer.encode(rawPassword);
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return PasswordConfigurer.matchPassword(rawPassword, encodedPassword);

    }

    public static BaseUtils getInstance() {
        if (instance == null) {
            instance = new BaseUtils();
        }
        return instance;
    }
}