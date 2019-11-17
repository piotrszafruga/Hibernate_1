package pl.coderslab.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class GenericDao<T> {

    @PersistenceContext
    EntityManager entityManager;

    public void save(T entity) {
        //
        entityManager.merge(entity);
    }

    public void edit(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.merge(entity);
        }
    }

    public T findById(Class<T> targetClass, long id) {
        //
        return entityManager.find(targetClass, id);
    }

    public void delete(T entity) {
        entityManager.remove(entityManager.contains(entity) ?
                entity : entityManager.merge(entity));
    }

}
