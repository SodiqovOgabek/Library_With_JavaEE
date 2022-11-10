package com.company.library_javaEE.dto;

import com.company.library_javaEE.enums.BookStatus;
import lombok.*;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:20 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateDTO {
    private Long id;
    private String name;
    private String description;
    private Integer downloadCount;
    private BookStatus status;
}