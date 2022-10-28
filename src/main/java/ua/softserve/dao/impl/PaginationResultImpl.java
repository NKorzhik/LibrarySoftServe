package ua.softserve.dao.impl;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.PaginationDao;
import ua.softserve.model.Book;
import ua.softserve.model.PaginationResult;

import java.util.List;

@Repository
public class PaginationResultImpl implements PaginationDao {

    SessionFactory sessionFactory;

    public PaginationResultImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public PaginationResult<Book> paginate(int pageNumber) {
        int pageSize = 3;
        int lastPageNumber;
        Long totalRecords;
        List<Book> bookList;

        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            TypedQuery<Long> countQuery = session.createQuery("SELECT COUNT (b.id) from Book b", Long.class);
            totalRecords = countQuery.getSingleResult();

            if (totalRecords % pageSize == 0) {
                lastPageNumber = (int) (totalRecords / pageSize);
            } else {
                lastPageNumber = (int) (totalRecords / pageSize) + 1;
            }

            session.getTransaction().commit();
        }


        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();

            TypedQuery<Book> query = session.createQuery("Select b From Book b LEFT JOIN FETCH b.coAuthor LEFT JOIN FETCH b.author  ORDER BY b.id",
                    Book.class);

            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            bookList = query.getResultList();

            session.getTransaction().commit();
        }

        PaginationResult<Book> result = new PaginationResult<>();
        result.setCurrentPageNumber(pageNumber);
        result.setPageSize(pageSize);
        result.setLastPageNumber(lastPageNumber);
        result.setTotalRecords(totalRecords);
        result.setRecords(bookList);

        return result;
    }


}
