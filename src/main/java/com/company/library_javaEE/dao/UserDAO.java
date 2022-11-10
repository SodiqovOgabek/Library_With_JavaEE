package com.company.library_javaEE.dao;

import com.company.library_javaEE.domains.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 11:40 PM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDAO extends GenericDAO<User, Long> {
    private static UserDAO instance;

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO ();
        }
        return instance;
    }

    public Optional<User> findByEmail(String email) {
        Session session = getSession ();
        session.beginTransaction ();
        User user = session.createQuery ( "select t from User t where t.email=:email", User.class )
                .setParameter ( "email", email )
                .getSingleResultOrNull ();
        session.getTransaction ();
        session.close ();
        return Optional.ofNullable ( user );
    }

}
