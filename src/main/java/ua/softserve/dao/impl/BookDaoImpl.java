package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.BookDao;
import ua.softserve.model.Book;

import java.util.List;

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
            bookList = session.createQuery("select b from Book b LEFT JOIN FETCH b.coAuthors LEFT JOIN FETCH " +
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
            book = session.createQuery("select b from Book b LEFT JOIN FETCH b.coAuthors LEFT JOIN FETCH " +
                            "b.author where b.id=:id",Book.class)
                    .setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        }
        return book;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH " +
                            "b.coAuthors WHERE b.title like :title or b.author.name like :title or b.author.surname like :title or b.coAuthors.name like :title or b.coAuthors.surname like: title", Book.class)
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
            session.getTransaction().commit();
            return books;
        }
    }
}
