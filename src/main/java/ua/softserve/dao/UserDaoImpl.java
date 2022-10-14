package ua.softserve.dao;

import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.model.User;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);


    private final SessionFactory sessionFactory;


    public UserDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

//    public SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    @Resource(name = "sessionFactory")
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User saved successfully");
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(user);
        logger.info("User updated successfully");
    }

    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaQuery<User> cq = session.getCriteriaBuilder().createQuery(User.class);
        cq.from(User.class);

        List<User> personsList = session.createQuery(cq).getResultList();

        for (User p : personsList) {
            logger.info("Person List::" + p);
        }

        return personsList;
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
    }
}
