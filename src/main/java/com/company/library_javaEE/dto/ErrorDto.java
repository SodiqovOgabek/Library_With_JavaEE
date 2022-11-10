package com.company.library_javaEE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:22 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ErrorDto {
    private String message;
    private String detailedMessage;
    private String path;
    @Builder.Default
    private Timestamp timestamp = Timestamp.valueOf ( LocalDateTime.now());
}