package com.company.library_javaEE.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:21 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@Data
@Builder
public class UploadsDTO {
    private Long id;
    private String originalName;
    private String generatedName;
    private String contentType;
    private String path;
    private long size;
}