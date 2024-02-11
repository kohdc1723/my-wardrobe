package spring.mywardrobe.mapper;

import spring.mywardrobe.domain.Keyword;
import spring.mywardrobe.dto.keyword.CreateKeywordRequest;
import spring.mywardrobe.dto.keyword.KeywordResponse;

public class KeywordMapper {
    public static KeywordResponse mapToKeywordResponse(Keyword keyword) {
        return new KeywordResponse(
                keyword.getId(),
                keyword.getName()
        );
    }
}
