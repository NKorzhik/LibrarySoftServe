package ua.softserve.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.softserve.model.*;

//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackages = "ua.softserve")
public class HibernateConfig {

    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;

    static {
        if (sessionFactory == null) {
            try {
                // Create StandardServiceRegistry
                standardServiceRegistry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
                // Create MetadataSources
                MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
                // Create Metadata
                //Metadata metadata = metadataSources.getMetadataBuilder().build();
                Metadata metadata = metadataSources
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Book.class)
                        .addAnnotatedClass(HistoryOfRequest.class)
                        .addAnnotatedClass(Quantity.class)
                        .addAnnotatedClass(Author.class).getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (standardServiceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
                }
            }
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    //-------------------------------------------------------------------------------//

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


    //------------------------------------------------------------------------//

//    private static SessionFactory sessionFactory;
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration = new Configuration();
//
//                // Hibernate settings equivalent to hibernate.cfg.xml's properties
//                Properties settings = new Properties();
//                settings.put(Environment.DRIVER, "org.postgresql.Driver");
//                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/library_softserve");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
//
//                //Add here your own username
//                settings.put(Environment.USER, "postgres");
//                settings.put(Environment.PASS, "NIKITA");
//
//                settings.put(Environment.SHOW_SQL, "true");
//                settings.put(Environment.FORMAT_SQL, "true");
//                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//
//                settings.put(Environment.HBM2DDL_AUTO, "update");
//
//                configuration.setProperties(settings);
//
//                configuration
//                        .addAnnotatedClass(User.class)
//                        .addAnnotatedClass(Book.class)
//                        .addAnnotatedClass(HistoryOfRequest.class)
//                        .addAnnotatedClass(Quantity.class)
//                        .addAnnotatedClass(Author.class);
//
//
////                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
////                        .applySettings(configuration.getProperties()).build();
//                // sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .configure(String.valueOf(configuration)).build();
//                Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
//                sessionFactory = metadata.getSessionFactoryBuilder().build();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return sessionFactory;
//    }
}