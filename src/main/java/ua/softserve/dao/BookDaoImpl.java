package ua.softserve.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.softserve.config.HibernateConfig;
import ua.softserve.model.Book;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private SessionFactory sessionFactory;

    public BookDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    //    public synchronized SessionFactory getSessionFactory(SessionFactory sessionFactory) {
    //        if (sessionFactory == null) {
    //            sessionFactory = HibernateConfig.getSessionFactory();
    //        }
    //        return sessionFactory;
    //    }

    @Override
    @Transactional
    public void addBook(Book book) {
        //Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            //transaction.commit();
        } catch (Exception e) {
//            if (//transaction != null) {
//                //transaction.rollback();
//            }
        }
    }

    @Override
    public void deleteBook(long id) {
        Session session = sessionFactory.openSession();
        session.remove(id);
        session.close();
    }

    @Override
    public List<Book> listBook() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Book", Book.class).getResultList();
    }
}
