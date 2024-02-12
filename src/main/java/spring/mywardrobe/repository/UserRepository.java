package spring.mywardrobe.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.mywardrobe.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager em;

    public Long save(User user) {
        em.persist(user);

        return user.getId();
    }

    public User findById(Long id) {
        return em.createQuery(
                "select u from User u" +
                " where u.id = :id and u.isDeleted = false", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
