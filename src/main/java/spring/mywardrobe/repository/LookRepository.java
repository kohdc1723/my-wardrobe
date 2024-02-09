package spring.mywardrobe.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.mywardrobe.domain.Look;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LookRepository {
    private final EntityManager em;

    public Long save(Look look) {
        em.persist(look);

        return look.getId();
    }

    public Look findOne(Long id) {
        return em.find(Look.class, id);
    }

}
