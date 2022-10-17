package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.QuantityDao;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;
import ua.softserve.model.enums.Type;

public class QuantityDaoImpl implements QuantityDao {

    private final SessionFactory sessionFactory;

    public QuantityDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public void addQuantity(Book book, int count) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (int i = 0; i < count; i++) {
                session.persist(new Quantity(book, Type.FREE));
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteOneQuantity(long bookId) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Quantity> query = session.createQuery(
                    "select q from Quantity q where q.bookId.id=:bookId",
                    Quantity.class).setMaxResults(1);
            query.setParameter("bookId", bookId);
            session.remove(query.getSingleResult());
            session.getTransaction().commit();
        }
    }

}
