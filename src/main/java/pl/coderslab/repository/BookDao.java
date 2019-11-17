package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book entity) {
        entityManager.merge(entity);
    }

    public void editBook(Book entity) {
        if (entityManager.contains(entity)) {
            entityManager.merge(entity);
        }
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void deleteBook(Book entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

    public List<Book> getRatingList(int number) {

        Query queryp = entityManager.createQuery("SELECT b FROM Book b where rating = :rating");
        queryp.setParameter("rating", number);

        List<Book> booksp = queryp.getResultList();

        return booksp;
    }


}
