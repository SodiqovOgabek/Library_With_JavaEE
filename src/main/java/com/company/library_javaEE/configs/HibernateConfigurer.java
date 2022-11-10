package com.company.library_javaEE.configs;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.reflections.Reflections;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 12:59 AM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateConfigurer {
    private static SessionFactory sessionFactory;

    private static SessionFactory   setup() {
        StandardServiceRegistry register = null;

        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder ();

                Properties properties = new Properties ();
                properties.load ( new FileReader ( "C:\\Users\\ogabe\\IdeaProjects\\nazariyaa\\Library_JavaEE\\src\\main\\resources\\datasource.properties" ) );

                registryBuilder.applySettings ( properties );
                register = registryBuilder.build ();

                MetadataSources sources = new MetadataSources ( register );

                Reflections reflections = new Reflections ( "com/company/library_javaEE/domains" );
                reflections.getTypesAnnotatedWith ( Entity.class )
                        .forEach ( clazz -> sources.addAnnotatedClassName ( clazz.getName () ) );

                Metadata metadata = sources.getMetadataBuilder ().build ();
                return metadata.getSessionFactoryBuilder ().build ();

            } catch (Exception e) {
                if (Objects.nonNull ( register )) {
                    StandardServiceRegistryBuilder.destroy ( register );
                }
                throw new RuntimeException ( "Hibernate exception %s".formatted ( e.getMessage () ) );
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed ()) {
            sessionFactory = setup ();
        }
        return sessionFactory;
    }
}
