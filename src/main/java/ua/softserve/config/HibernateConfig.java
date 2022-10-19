package ua.softserve.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.softserve.model.*;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "ua.softserve")
public class HibernateConfig {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/library_softserve");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                //Add here your own username
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "NIKITA");
                //settings.put(Environment.PASS, "1234");

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");
                //settings.put(Environment.AUTOCOMMIT, "true");
                //settings.put(Environment.PERSISTENCE_UNIT_NAME, "manager");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Book.class)
                        .addAnnotatedClass(Author.class)
                        .addAnnotatedClass(HistoryOfRequest.class)
                        .addAnnotatedClass(Quantity.class)
                        .addAnnotatedClass(User.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
