package com.company.library_javaEE.dto;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 1:19 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */

import com.company.library_javaEE.enums.Genre;
import com.company.library_javaEE.enums.Language;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateDTO {
    private String name;
    private String description;
    private String author;
    private Genre genre;
    private Language language;
    private int pageCount;
}
