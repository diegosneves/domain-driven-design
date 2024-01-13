package diegosneves.ddd.github.infrastructure.db.mysql.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateConnectionSingleton {

    private static SessionFactory sessionFactory;

    private HibernateConnectionSingleton(){}
    private static SessionFactory getSessionFactory(Configuration configuration) {
        try {
            // Cria a fábrica de sessões
            return configuration.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {
        if (sessionFactory == null) {
            // Carrega as configurações do hibernate.cfg.xml
            sessionFactory = getSessionFactory(new Configuration().configure());
        }
        return sessionFactory.openSession();
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

}
