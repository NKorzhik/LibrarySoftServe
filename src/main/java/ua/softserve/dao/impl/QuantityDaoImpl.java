package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.QuantityDao;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;
import ua.softserve.model.enums.Type;

@Repository
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
    public void deleteOneCopyById(long bookId) {
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

    @Override
    public long getCountOfQuantityByBookId(long bookId) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<Quantity> query = session.createQuery(
                    "select q from Quantity q where q.bookId.id =:bookId and q.type =: type",
                    Quantity.class);
            query.setParameter("bookId",bookId);
            query.setParameter("type", Type.FREE);
            long result = query.getResultStream().count();
            session.getTransaction().commit();
            return result;
        }
    }

}
