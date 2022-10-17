package ua.softserve.dao.impl;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.QuantityDao;
import ua.softserve.model.Book;
import ua.softserve.model.Quantity;
import ua.softserve.model.enums.Type;

import java.util.List;


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
    public void deleteOneQuantity(long id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Quantity> cr = cb.createQuery(Quantity.class);
            Root<Quantity> root = cr.from(Quantity.class);
            cr.select(root);

            Query<Quantity> query = session.createQuery(cr);
            List<Quantity> results = query.getResultList();
            session.remove(results.get(0));
            session.getTransaction().commit();
        }
    }
}
