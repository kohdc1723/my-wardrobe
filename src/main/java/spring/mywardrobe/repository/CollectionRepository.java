package spring.mywardrobe.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Collection;

import java.util.List;

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

    public Collection findOne(Long id) {
        return em.find(Collection.class, id);
    }
}
