package spring.mywardrobe.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.mywardrobe.domain.Collection;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CollectionRepository {
    private final EntityManager em;

    public void save(Collection collection) {
        em.persist(collection);
    }

    public void saveAll(List<Collection> collectionList) {
        collectionList.forEach(em::persist);
    }

    public Collection findById(Long id) {
        return em.find(Collection.class, id);
    }

    public List<Collection> findByUser(Long userId) {
        return em.createQuery(
                "select c from Collection c" +
                        " where c.user.id = :userId", Collection.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void delete(Long id) {
        Collection collection = em.find(Collection.class, id);

        em.remove(collection);
    }
}
