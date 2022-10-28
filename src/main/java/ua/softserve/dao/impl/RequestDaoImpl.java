package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.RequestDao;
import ua.softserve.model.HistoryOfRequest;
import ua.softserve.model.Quantity;
import ua.softserve.model.enums.Status;
import ua.softserve.model.enums.Type;

import java.time.LocalDate;
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
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(request);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void acceptRequest(HistoryOfRequest request) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(request);
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
                                    "left join fetch r.bookId.author left join fetch r.bookId.coAuthor left join fetch r.userId where r.userId.id =:id",
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
            HistoryOfRequest request = session.createQuery("select r from HistoryOfRequest r " +
                            "left join fetch r.bookId left join fetch r.bookId.author left join fetch r.bookId.coAuthor " +
                            "left join fetch r.userId where r.id =:id", HistoryOfRequest.class)
                    .setParameter("id",id).getSingleResult();
            session.getTransaction().commit();
            return request;
        }
    }
    @Override
    public List<HistoryOfRequest> getBooksWithStatusWaiting() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<HistoryOfRequest> request =
                    session.createQuery("select r from HistoryOfRequest r left join fetch r.bookId left join fetch r.bookId.author left join fetch r.bookId.coAuthor where r.status =: status", HistoryOfRequest.class)
                            .setParameter("status", WAITING).getResultList();
            session.getTransaction().commit();
            return request;
        }
    }

    @Override
    public void returnBookToLibrary(long requestId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            HistoryOfRequest request = session.createQuery("select r from HistoryOfRequest r left join fetch r.bookId left join fetch r.bookId.author left join fetch r.bookId.coAuthor where r.id =:requestId",
                            HistoryOfRequest.class)
                    .setParameter("requestId", requestId).getSingleResult();

            Quantity quantity = session.createQuery("select q.bookId.quantities from HistoryOfRequest q where q.id =: requestId", Quantity.class)
                    .setParameter("requestId", requestId).setMaxResults(1).getSingleResult();

            quantity.setType(Type.FREE);

            request.setReturnDate(LocalDate.now());

            int result = LocalDate.now().compareTo(request.getShouldBeReturn());
            if (result > 0) {
                request.setStatus(Status.RETURNED_NOT_ON_TIME);
            } else {
                request.setStatus(Status.RETURNED_ON_TIME);
            }

            session.merge(quantity);
            session.merge(request);
            session.getTransaction().commit();
        }
    }


}