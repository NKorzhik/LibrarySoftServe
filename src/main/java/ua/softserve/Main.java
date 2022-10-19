package ua.softserve;

import ua.softserve.dao.AuthorDao;
import ua.softserve.dao.BookDao;
import ua.softserve.dao.QuantityDao;
import ua.softserve.dao.impl.AuthorDaoImpl;
import ua.softserve.dao.impl.BookDaoImpl;
import ua.softserve.dao.impl.QuantityDaoImpl;
import ua.softserve.model.Author;
import ua.softserve.model.Book;
import ua.softserve.model.enums.Genre;
import ua.softserve.service.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        AuthorDao authorDao = new AuthorDaoImpl();
        BookDao bookDao = new BookDaoImpl();
        QuantityDao quantityDao = new QuantityDaoImpl();

       /* Author author = new Author();
        author.setName("nikita2");
        author.setSurname("korzh2");*/

        //CREATE BOOK


//        Author author = new Author();
//        author.setName("Jane");
//        author.setSurname("Austen");
//        authorDao.addAuthor(author);
////
//        Author coAuthor = new Author();
//        coAuthor.setName("Man2");
//        coAuthor.setSurname("Manovich2");
//        authorDao.addAuthor(coAuthor);
////
//        Book book = new Book();
//        book.setTitle("Pride and Prejudice");
//        book.setDescription("Pride and Prejudice is an 1813 novel of manners by Jane Austen. " +
//                "The novel follows the character development of Elizabeth Bennet, the dynamic protagonist of the book " +
//                "who learns about the repercussions of hasty judgments and comes to appreciate the difference between " +
//                "superficial goodness and actual goodness.");
//        book.setISBN("3114567894445");
//        book.setGenre(Genre.NOVEL);
//        book.setAuthor(authorDao.getAuthor(2));
//        book.setCoAuthors(authorDao.getAuthor(1));
//        bookDao.addBook(book);



//        Author author = authorDao.getAuthor(book.getAuthor().getId());
//
//        System.out.println(author.getId() +
//                author.getName() + author.getSurname());



//        List<Book> lis  = bookService.listBook();
//
//        for (Book b : lis) {
//            //System.out.println(authorDao.getAuthor(b.getAuthor().getId()).getName());
//            System.out.println(b.getCoAuthors().getId());
//        }
//        //CREATE QUANTITY
//        quantityDao.addQuantity(book, 5);
//        quantityDao.deleteOneQuantity(9);
//        System.out.println(quantityDao.getCountOfQuantityByBookId(book.getId()));

        //DELETE BOOK WITH QUANTITY
        //bookDao.deleteAllCopiesBook(book.getId());
        //quantityDao.deleteOneQuantity(4);
    }
}
