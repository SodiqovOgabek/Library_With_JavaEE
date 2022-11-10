package com.company.library_javaEE.configs;

import com.company.library_javaEE.dao.BookDAO;
import com.company.library_javaEE.dao.UploadDAO;
import com.company.library_javaEE.dao.UserDAO;
import com.company.library_javaEE.service.auth.UserService;
import com.company.library_javaEE.service.file.FileStorageService;
import com.company.library_javaEE.service.book.BookService;
import com.company.library_javaEE.utils.BaseUtils;


/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:18 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class ApplicationContextHolder {
    public static <T> T getBean(String beanName) {
        return switch (beanName) {
            case "BaseUtils" -> (T) BaseUtils.getInstance();
            case "UserServiceImpl" -> (T) UserService.getInstance();
            case "BookServiceImpl" -> (T) BookService.getInstance();
            case "FileStorageServiceImpl" -> (T) FileStorageService.getInstance();
            case "UserDAO" -> (T) UserDAO.getInstance();
            case "BookDAO" -> (T) BookDAO.getInstance();
            case "UploadDAO" -> (T) UploadDAO.getInstance();
            default -> throw new RuntimeException("Bean Not Found %s".formatted(beanName));
        };
    }

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);

    }
}
