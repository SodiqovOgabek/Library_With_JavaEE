package com.company.library_javaEE.domains;

import com.company.library_javaEE.enums.BookStatus;
import com.company.library_javaEE.enums.Genre;
import com.company.library_javaEE.enums.Language;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:25 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String author;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus = BookStatus.PENDING;

    @Enumerated(EnumType.STRING)
    private Language language;
    private Integer pageCount;
    @Builder.Default
    private Integer downloadCount=0;

    @OneToOne(fetch = FetchType.EAGER)
    private Uploads uploads;

    @OneToOne(fetch = FetchType.EAGER)
    private Uploads file;
}
