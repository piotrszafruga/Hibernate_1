package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Author;
import pl.coderslab.repository.AuthorDao;
import pl.coderslab.repository.GenericDao;

@Controller
public class AuthorController {

    private final GenericDao<Author> authorDao;

    @Autowired
    public AuthorController(GenericDao<Author> authorDao) {
        this.authorDao = authorDao;
    }


    @RequestMapping("/addAuthor")
    @ResponseBody
    public String saveAuthor() {    // (@RequestBody Author author)
        Author author = new Author();
        author.setFirstName("Jan");
        author.setLastName("Kowalski");

        authorDao.save(author);
        return author.toString();
    }

    @RequestMapping("/findAuthor/{id}")
    @ResponseBody
    public Author findAuthor(@PathVariable long id) {
        //
        return authorDao.findById(Author.class, id);
    }

    @RequestMapping("/editAuthor")
    @ResponseBody
    public String editAuthor(@RequestBody Author author) {
        authorDao.edit(author);
        return author.toString();
    }

    @RequestMapping("/deleteAuthor/{id}")
    @ResponseBody
    public String deleteAuthor(@PathVariable Author author) {
        authorDao.delete(author);
        return author.toString();
    }

}
