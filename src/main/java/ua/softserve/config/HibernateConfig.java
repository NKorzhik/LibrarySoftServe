package ua.softserve.config;

import java.io.IOException;
import java.util.Properties;


import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;
import ua.softserve.model.User;

import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = "ua.softserve")
public class HibernateConfig {

//    private static final Logger logger = LoggerFactory.getLogger(HibernateConfig.class);
//
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
//
//    @Bean
//    public DataSource dataSource(){
//        try(BasicDataSource dataSource = new BasicDataSource()) {
//           dataSource.setDriverClassName("org.postgresql.Driver");
//           dataSource.setUrl("jdbc:postgresql://localhost:5432/library_softserve");
//           dataSource.setUsername("postgres");
//           dataSource.setPassword("NIKITA");
//           return dataSource;
//        } catch (Exception e){
//            logger.error("DBCP DataSource bean cannot be created!", e);
//            return null;
//        }
//    }
//
//    private Properties hibernateProperties() {
//        Properties hibernateProp = new Properties();
//        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//
//        //create, create-drop, update, validate
//        hibernateProp.put("hibernate.hbm2ddl.auto", "update");
//        hibernateProp.put("hibernate.format_sql", true);
//        hibernateProp.put("hibernate.use_sql_comments", true);
//        hibernateProp.put("hibernate.show_sql", true);
////        hibernateProp.put("hibernate.max_fetch_depth", 3);
////        hibernateProp.put("hibernate.jdbc.batch_size", 10);
////        hibernateProp.put("hibernate.jdbc.fetch_size", 50);
//        return hibernateProp;
//    }
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        return new LocalSessionFactoryBuilder(dataSource())
//                .scanPackages("ua.softserve")
//                .addProperties(hibernateProperties())
//                .buildSessionFactory();
//    }
//
//    @Bean public PlatformTransactionManager transactionManager() throws IOException {
//        return new HibernateTransactionManager(sessionFactory());
//    }

//    @Bean(destroyMethod = "destroy")
//    public CleanUp cleanUp() {
//        return new CleanUp(new JdbcTemplate(dataSource()));
//    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/library_softserve");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                //Add here your own username
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "NIKITA");


                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Book.class)
                        .addAnnotatedClass(HibernateConfig.class)
                        .addAnnotatedClass(Quantity.class)
                        .addAnnotatedClass(Author.class);


//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
                // sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .configure(String.valueOf(configuration)).build();
                Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}