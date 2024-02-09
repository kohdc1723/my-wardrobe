package spring.mywardrobe.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import spring.mywardrobe.domain.*;
import spring.mywardrobe.service.ClothFilter;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ClothRepository {
    private final EntityManager em;

    public void save(Cloth cloth) {
        em.persist(cloth);
    }

    public Cloth findOne(Long clothId) {
        return em.find(Cloth.class, clothId);
    }

    public List<Cloth> findAll(ClothFilter clothFilter) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QCloth cloth = QCloth.cloth;
        QUser user = QUser.user;
        QCollection collection = QCollection.collection;

        return query
                .select(cloth)
                .from(cloth)
                .leftJoin(cloth.user, user)
                .leftJoin(cloth.collection, collection).fetchJoin()
                .where(userEq(clothFilter.getUserId()),
                        collectionEq(clothFilter.getCollectionId()),
                        nameContains(clothFilter.getName()),
                        brandContains(clothFilter.getBrand()),
                        seasonsContains(clothFilter.getSeasons()))
                .fetch();
    }

    public void deleteOne(Long id) {
        Cloth cloth = em.find(Cloth.class, id);

        em.remove(cloth);
    }

    private BooleanExpression userEq(Long userId) {
        if (userId == null) {
            return null;
        }

        return QCloth.cloth.user.id.eq(userId);
    }

    private BooleanExpression collectionEq(Long collectionId) {
        if (collectionId == null) {
            return null;
        }

        return QCloth.cloth.collection.id.eq(collectionId);
    }

    private BooleanExpression nameContains(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return QCloth.cloth.name.containsIgnoreCase(name);
    }

    private BooleanExpression brandContains(String brand) {
        if (!StringUtils.hasText(brand)) {
            return null;
        }

        return QCloth.cloth.brand.containsIgnoreCase(brand);
    }

    private BooleanExpression seasonsContains(List<Season> seasons) {
        if (seasons == null || seasons.isEmpty()) {
            return null;
        }

        return QCloth.cloth.seasons.any().in(seasons);
    }
}
