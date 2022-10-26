package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.RequestDao;
import ua.softserve.model.HistoryOfRequest;

import java.util.List;

import static ua.softserve.model.enums.Status.WAITING;

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
    @Override
    public List<HistoryOfRequest> getRequestedBooks(long id) {
        List<HistoryOfRequest> list;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            list = session.createQuery("SELECT r from HistoryOfRequest r left join fetch r.bookId " +
                                    "left join fetch r.bookId.author left join fetch r.bookId.coAuthor where r.userId.id =:id",
                            HistoryOfRequest.class)
                    .setParameter("id", id)
                    .getResultStream()
                    .toList();
            session.getTransaction().commit();
        }
        return list;
    }

    @Override
    public HistoryOfRequest getRequestById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            HistoryOfRequest request = session.createQuery("select r from HistoryOfRequest r left join fetch r.bookId left join fetch r.bookId.author left join fetch r.bookId.coAuthor where r.id =:id", HistoryOfRequest.class)
                    .setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
            return request;
        }
    }

    @Override
    public List<HistoryOfRequest> getBooksWithStatusWaiting() {
       try(Session session = sessionFactory.openSession()){
           session.beginTransaction();
           List<HistoryOfRequest> request =
                   session.createQuery("select r from HistoryOfRequest r left join fetch r.bookId left join fetch r.bookId.author left join fetch r.bookId.coAuthor where r.status =: status",HistoryOfRequest.class)
                           .setParameter("status",WAITING).getResultList();
           session.getTransaction().commit();
           return request;
       }
    }
}