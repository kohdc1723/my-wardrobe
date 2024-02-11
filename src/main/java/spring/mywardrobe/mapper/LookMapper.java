package spring.mywardrobe.mapper;

import spring.mywardrobe.domain.*;
import spring.mywardrobe.dto.cloth.ClothResponse;
import spring.mywardrobe.dto.keyword.KeywordResponse;
import spring.mywardrobe.dto.look.LookCreateRequest;
import spring.mywardrobe.dto.look.LookResponse;

import java.util.List;

public class LookMapper {
    public static LookResponse mapToLookResponse(Look look) {
        List<ClothResponse> clothResponseList = look.getClothLooks().stream()
                .map(ClothLook::getCloth)
                .map(ClothMapper::mapToClothResponse)
                .toList();

        List<KeywordResponse> keywordResponseList = look.getKeywordLooks().stream()
                .map(KeywordLook::getKeyword)
                .map(KeywordMapper::mapToKeywordResponse)
                .toList();

        return new LookResponse(
                look.getId(),
                look.getName(),
                clothResponseList,
                keywordResponseList
        );
    }
}
