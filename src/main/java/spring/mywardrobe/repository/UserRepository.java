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

    public List<User> findAll() {
        return em.createQuery("select u from User u where u.isDeleted = false", User.class)
                .getResultList();
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }
}
