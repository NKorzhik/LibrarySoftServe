package ua.softserve.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.softserve.config.HibernateConfig;
import ua.softserve.model.Author;

public class AuthorDaoImpl implements AuthorDao {

    private final SessionFactory sessionFactory;

    public AuthorDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public void addAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
