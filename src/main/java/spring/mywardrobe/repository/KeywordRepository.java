package spring.mywardrobe.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.mywardrobe.domain.Cloth;
import spring.mywardrobe.domain.Keyword;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class KeywordRepository {
    private final EntityManager em;

    public Long save(Keyword keyword) {
        em.persist(keyword);

        return keyword.getId();
    }

    public Keyword findById(Long id) {
        return em.find(Keyword.class, id);
    }

    public List<Keyword> findAllByIds(List<Long> keywordIds) {
        return em.createQuery(
                        "select k from Keyword k" +
                                " where k.id in :keywordIds", Keyword.class)
                .setParameter("keywordIds", keywordIds)
                .getResultList();
    }

    public List<Keyword> findByUser(Long userId) {
        return em.createQuery(
                "select k from Keyword k" +
                " where k.user.id = :userId", Keyword.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void delete(Long id) {
        Keyword keyword = em.find(Keyword.class, id);

        em.remove(keyword);
    }
}
