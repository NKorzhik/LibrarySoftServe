package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.ListUtils;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.BookDao;
import ua.softserve.model.Book;
import ua.softserve.model.HistoryOfRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class BookDaoImpl implements BookDao {
    private final SessionFactory sessionFactory;


    public BookDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public void addBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Book book = session.getReference(Book.class, id);
            session.remove(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> listBook() {
        List<Book> bookList;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bookList = session.createQuery("select b from Book b LEFT JOIN FETCH b.coAuthor LEFT JOIN FETCH " +
                    "b.author order by b.id", Book.class).getResultList();

            session.getTransaction().commit();
        }
        return bookList;
    }

    @Override
    public Book getBook(long id) {
        Book book;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //ДОБАВИТЬ QUANTITY
            book = session.createQuery("select b from Book b LEFT JOIN FETCH b.coAuthor LEFT JOIN FETCH " +
                            "b.author where b.id=:id", Book.class)
                    .setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        }
        return book;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Book> books = session.createQuery("select b from Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH " +
                            "b.coAuthor WHERE b.title like :title or b.author.name like :title or b.author.surname like :title or b.coAuthor.name like :title or b.coAuthor.surname like: title", Book.class)
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
            session.getTransaction().commit();
            return books;
        }
    }

    @Override
    public List<Book> getPopularBookInSelectedPeriod(String firstDate, String secondDate) {

        try (Session session = sessionFactory.openSession()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            session.getTransaction().begin();

            List<Book> mostPopular = session.createQuery("Select b.bookId from HistoryOfRequest b  left join  b.bookId left join b.bookId.author  left join  b.bookId.coAuthor  where b.dateOfIssue between :firstDate AND :secondDate group by b.bookId order by count (b.bookId) desc ", Book.class)
                    .setParameter("firstDate", LocalDate.parse(firstDate,formatter))
                    .setParameter("secondDate", LocalDate.parse(secondDate,formatter)).getResultList();

            session.getTransaction().commit();
            return mostPopular;
        }
    }

    @Override
    public List<Book> getUnpopularBookInSelectedPeriod(String firstDate, String secondDate) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            List<Book> mostUnpopular = session.createQuery("Select b.bookId from HistoryOfRequest b left join  b.bookId left join b.bookId.author left join b.bookId.coAuthor  where b.dateOfIssue between :firstDate AND :secondDate group by b.bookId order by count (b.bookId)", Book.class)
                    .setParameter("firstDate",LocalDate.parse(firstDate,formatter))
                    .setParameter("secondDate", LocalDate.parse(secondDate,formatter)).getResultList();

            session.getTransaction().commit();
            return mostUnpopular;
        }
    }

}
