package pl.coderslab.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.GenericDao;

import java.util.Arrays;

@Controller
public class BookController {

    private GenericDao<Book> bookDao;

    @Autowired
    public BookController(GenericDao<Book> bookDao) {
        this.bookDao = bookDao;
    }


    @RequestMapping("/addBook")
    @ResponseBody
    public String addBook() {
        Book book = new Book();
        book.setTitle("title1");
        book.setDescription("desc1");
        book.setRating(5);

        Author author = new Author();
        author.setFirstName("Andrzej");
        author.setLastName("Malinowski");
        book.setAuthorList(Arrays.asList(author));

        Publisher publisher = new Publisher();
        publisher.setName("NowyPublisher1");
        publisher.getBookList().add(book);

        book.setPublisher(publisher);

        bookDao.save(book);
        return book.toString();
    }

    @RequestMapping("/findBook/{id}")
    @ResponseBody
    public String findBook(@PathVariable long id) {
        Book book = bookDao.findById(Book.class, id);
        Hibernate.initialize(book.getPublisher());
        return book.toString();
    }

    @RequestMapping("/editBook")
    public String editBook(@RequestBody Book book) {
        //
        bookDao.edit(book);
        return book.toString();
    }

    @RequestMapping("/deleteBook")
    public String deleteBook(@RequestBody Book book) {
        //
        bookDao.delete(book);
        return book.toString();
    }
}
