package com.sport.infrastructure;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        var standardRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        var metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
        sessionFactory = metaData.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}