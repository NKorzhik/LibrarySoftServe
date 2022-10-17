package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.transaction.annotation.Transactional;
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
        }
    }

    @Override
    public void deleteOneQuantity(long id) {

    }
}
