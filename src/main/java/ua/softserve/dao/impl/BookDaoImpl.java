package ua.softserve.dao.impl;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.BookDao;
import ua.softserve.model.Author;
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
            //bookList = session.createQuery("select b from Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.coAuthors", Book.class).getResultList();
            bookList = session.createQuery("select b from Book b LEFT JOIN FETCH b.coAuthors LEFT JOIN FETCH b.author order by b.id", Book.class).getResultList();

            session.getTransaction().commit();
        }
        return bookList;
    }

    @Override
    public Book getBook(long id) {
        Book book;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //book = session.get(Book.class, id);
            book = session.createQuery("select b from Book b LEFT JOIN FETCH b.coAuthors LEFT JOIN FETCH b.author where b.id=:id",Book.class).
                    setParameter("id", id).getSingleResult();
            session.getTransaction().commit();
        }
        return book;
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book b LEFT JOIN FETCH b.author LEFT JOIN FETCH b.coAuthors author WHERE b.title like :title", Book.class)
                    .setParameter("title", "%" + title + "%").getResultList();
            session.getTransaction().commit();
            return books;
        }
    }

    @Override
    public List<Book> findBookByAuthor(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Book> books = session.createQuery("from Book b WHERE b.author.id=:id or b.coAuthors.id =:id", Book.class)
                    .setParameter("id", id).getResultList();
            session.getTransaction().commit();
            return books;
        }

    }


}
