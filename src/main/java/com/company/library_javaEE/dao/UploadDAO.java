package com.company.library_javaEE.dao;

import com.company.library_javaEE.domains.Uploads;
import com.company.library_javaEE.dto.UploadsDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Objects;
import java.util.Optional;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 11:32 PM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadDAO extends GenericDAO<Uploads, Long> {

    private static UploadDAO instance;

    public static UploadDAO getInstance() {
        if (instance == null) {
            instance = new UploadDAO ();
        }
        return instance;
    }


    public Optional<Uploads> getByPath(String path) {
        Session session = getSession ();
        session.beginTransaction ();
        Uploads uploads = session.createQuery ( "select t from Uploads t where t.path=:path", Uploads.class )
                .setParameter ( "path", path )
                .getSingleResultOrNull ();

        session.getTransaction ().commit ();
        session.close ();

        return Optional.ofNullable ( uploads );
    }

}
