package com.company.library_javaEE.domains;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/8/2022 12:36 AM (Tuesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private Integer number;
    private Integer totalPages;
    private Boolean hasPrevious;
    private Boolean hasNext;
}
