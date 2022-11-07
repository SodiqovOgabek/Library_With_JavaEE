package com.company.library_javaEE.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:32 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Uploads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;
    private String originalName;
    private String generatedName;
    private long size;
    private String contentType;
}
