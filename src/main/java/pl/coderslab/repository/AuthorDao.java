package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    public void saveAuthor(Author entity) {
        entityManager.merge(entity);
    }

    public void editAuthor(Author entity) {
        if (entityManager.contains(entity)) {
            entityManager.merge(entity);
        }
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public void deleteAuthor(long id) {
        entityManager.remove(findById(id));
    }
}
