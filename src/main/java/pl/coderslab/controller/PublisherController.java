package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.GenericDao;
import pl.coderslab.repository.PublisherDao;

@Controller
public class PublisherController {

    private final GenericDao<Publisher> publisherDao;

    @Autowired
    public PublisherController(GenericDao<Publisher> publisherDao) {
        //
        this.publisherDao = publisherDao;
    }


    @RequestMapping("/addPublisher")
    public String savePublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Publisher1");
        publisherDao.save(publisher);
        return publisher.toString();
    }

    @RequestMapping("/findPublisher/{id}")
    @ResponseBody
    public Publisher findPublisher(@PathVariable long id) {
        //
        return publisherDao.findById(Publisher.class, id);
    }

    @RequestMapping("/editPublisher")
    public String editPublisher(@RequestBody Publisher publisher) {
        //
        publisherDao.edit(publisher);
        return publisher.toString();
    }

    @RequestMapping("/deletePublisher/{id}")
    public String deletePublisher(@RequestBody Publisher publisher) {
        //
        publisherDao.delete(publisher);
        return publisher.toString();
    }
}
