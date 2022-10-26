package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.UserDao;
import ua.softserve.model.Book;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.User;

import java.util.List;
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

    @Override
    public void addUser(User user) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }



//    @Override
//    public List<HistoryOfRequest> findBook(String title) {
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            List<HistoryOfRequest> books = session.createQuery("from HistoryOfRequest r left join fetch r.bookId" +
//                            " left join fetch r.bookId.author left join fetch r.bookId.coAuthor where r.bookId.title like :title" +
//                            " or r.bookId.author.name like :title or r.bookId.author.surname like :title" +
//                            " or r.bookId.coAuthor.name like :title or r.bookId.coAuthor.surname like: title", HistoryOfRequest.class)
//                    .setParameter("title", "%" + title + "%")
//                    .getResultList();
//            session.getTransaction().commit();
//            return books;
//        }
//    }


}
