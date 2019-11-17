package pl.coderslab.repository;

import org.springframework.stereotype.Repository;
import pl.coderslab.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    public void savePublisher(Publisher entity) {
        entityManager.merge(entity);
    }

    public void editPublisher(Publisher entity) {
        if (entityManager.contains(entity)) {
            entityManager.merge(entity);
        }
    }

    public Publisher findById(long id) {
        //
        return entityManager.find(Publisher.class, id);
    }

    public void deletePublisher(Publisher entity) {
        //
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }
}
