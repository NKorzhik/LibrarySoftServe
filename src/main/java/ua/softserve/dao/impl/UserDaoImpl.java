package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.UserDao;
import ua.softserve.model.User;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.createQuery("select u from User u where u.email=: email", User.class)
                    .setParameter("email", email).getSingleResultOrNull();
            session.getTransaction().commit();
            return Optional.of(user);
        }
    }
}
