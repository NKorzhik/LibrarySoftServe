package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.BookDao;
import ua.softserve.model.Book;

import java.util.List;

@Repository
//@Transactional(readOnly = true)
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
    public void deleteAllCopiesBook(long id) {
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
    public void deleteOneCopyBook(long id) {
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
            bookList = session.createQuery("FROM Book", Book.class).getResultList();
            session.getTransaction().commit();
        }
        return bookList;
    }

    @Override
    public Book getBook(long id) {
        Book book;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            book = session.get(Book.class, id);
            session.getTransaction().commit();
        }
        return book;
    }
}
