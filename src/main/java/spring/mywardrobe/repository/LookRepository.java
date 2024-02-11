package spring.mywardrobe.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import spring.mywardrobe.domain.*;

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

    public List<Look> findAll(LookSearchOptions lookSearchOptions) {
        JPAQueryFactory query = new JPAQueryFactory(em);

        QLook look = QLook.look;
        QUser user = QUser.user;
        QClothLook clothLook = QClothLook.clothLook;
        QCloth cloth = QCloth.cloth;
        QKeywordLook keywordLook = QKeywordLook.keywordLook;
        QKeyword keyword = QKeyword.keyword;

        return query
                .select(look)
                .from(look)
                .join(look.user, user)
                .join(look.clothLooks, clothLook)
                .join(clothLook.cloth, cloth).fetchJoin()
                .join(look.keywordLooks, keywordLook)
                .join(keywordLook.keyword, keyword).fetchJoin()
                .where(userEq(lookSearchOptions.getUserId()),
                        nameContains(lookSearchOptions.getName()),
                        keywordContains(lookSearchOptions.getKeywords()))
                .fetch();
    }

    private BooleanExpression userEq(Long userId) {
        if (userId == null) {
            return null;
        }

        return QLook.look.user.id.eq(userId);
    }

    private BooleanExpression nameContains(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return QLook.look.name.containsIgnoreCase(name);
    }

    private BooleanExpression keywordContains(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            return null;
        }

        return keywords.stream()
                .map(QKeyword.keyword.name::containsIgnoreCase)
                .reduce(BooleanExpression::and)
                .orElse(null);
    }
}
