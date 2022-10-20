package ua.softserve.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.softserve.config.HibernateConfig;
import ua.softserve.dao.AuthorDao;
import ua.softserve.model.Author;

import java.util.List;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    private final SessionFactory sessionFactory;

    public AuthorDaoImpl() {
        this.sessionFactory = HibernateConfig.getSessionFactory();
    }

    @Override
    public long addAuthor(Author author) {
        long id = -1;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(author);
            session.flush();
            id = author.getId();
            session.getTransaction().commit();
        } catch (Exception e) {
            /*if (transaction != null) {
                transaction.rollback();
            }*/
        }
        return id;
    }

    @Override
    public Author getAuthor(long id) {
        Author author;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            author = session.get(Author.class, id);
            session.getTransaction().commit();
        }
        return author;
    }

    @Override
    public List<Author> getBothAuthorsByNameAndSurname(String mainAuthorName,
                                                   String mainAuthorSurname,
                                                   String coAuthorName,
                                                   String coAuthorSurname) {
        List<Author> authors;
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query<Author> query = session.createQuery(
                    "select a from Author a where (a.name =:mainAuthorName and a.surname =:mainAuthorSurname) or " +
                            "(a.name =: coAuthorName and a.surname =: coAuthorSurname)", Author.class);
            query.setParameter("mainAuthorName", mainAuthorName);
            query.setParameter("mainAuthorSurname", mainAuthorSurname);
            query.setParameter("coAuthorName", coAuthorName);
            query.setParameter("coAuthorSurname", coAuthorSurname);
            authors = query.list();
            session.getTransaction().commit();
        }
        return authors;
    }

    @Override
    public Optional<Author> getOneAuthorByNameAndSurname(String name, String surname) {
        Optional<Author> author;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<Author> query = session.createQuery(
                    "select a from Author a where a.name =:name and a.surname =:surname", Author.class);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            author = Optional.ofNullable(query.getSingleResultOrNull());
            session.getTransaction().commit();
            return author;
        }
    }
}
