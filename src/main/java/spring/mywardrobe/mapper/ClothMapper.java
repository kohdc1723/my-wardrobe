package spring.mywardrobe.mapper;

import spring.mywardrobe.domain.Cloth;
import spring.mywardrobe.dto.cloth.ClothResponse;
import spring.mywardrobe.dto.collection.CollectionResponse;

public class ClothMapper {
    public static ClothResponse mapToClothResponse(Cloth cloth) {
        return new ClothResponse(
                cloth.getId(),
                cloth.getName(),
                cloth.getBrand(),
                cloth.getImageUrl(),
                cloth.getSeasons(),
                cloth.getCollection().getName()
        );
    }
}
