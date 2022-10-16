package ua.softserve;

import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.AuthorDaoImpl;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.BookDaoImpl;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.enums.Genre;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        AuthorDao authorDao = new AuthorDaoImpl();
        Author author = new Author();
        author.setName("nikita2");
        author.setSurname("korzh2");

        authorDao.addAuthor(author);
        System.out.println(author.getId());


        BookDao bookDao = new BookDaoImpl();
        Book book = new Book();
        book.setTitle("Book8");
        book.setDescription("I love Hibernate");
        book.setISBN("7234567894445");
        book.setGenreList(List.of(Genre.A, Genre.ROMAN));
        bookDao.addBook(book);

        System.out.println(book.getId());

        List<Book> bookList = bookDao.listBook();
        for (Book b:bookList) {
            System.out.println(b.toString());
        }

    }
}
