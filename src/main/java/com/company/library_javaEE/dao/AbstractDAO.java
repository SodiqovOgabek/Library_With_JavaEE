package com.company.library_javaEE.dao;

/**
 * @author "Sodiqov Ogabek"
 * @since 11/9/2022 12:54 AM (Wednesday)
 * Library_JavaEE/IntelliJ IDEA
 */
public class AbstractDAO<D extends BaseDAO> {
    protected final D dao;


    public AbstractDAO(D a) {
        this.dao = a;
    }

}
