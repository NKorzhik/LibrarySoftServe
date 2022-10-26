package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.RequestDao;
import ua.softserve.model.HistoryOfRequest;

@Repository
public class RequestDaoImpl implements RequestDao {

    private final SessionFactory sessionFactory;

    public RequestDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public void addRequest(HistoryOfRequest request) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(request);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}