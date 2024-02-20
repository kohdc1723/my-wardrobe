package spring.mywardrobe.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import spring.mywardrobe.domain.User;

import java.util.List;
import java.util.Optional;

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

    public Optional<User> findByEmail(String email) {
        try {
            User user = em.createQuery(
                    "select u from User u" +
                            " where u.email = :email and u.isDeleted = false", User.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public boolean existsByEmail(String email) {
        List<User> userList = em.createQuery(
                "select u from User u" +
                        " where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();

        return !userList.isEmpty();
    }
}
