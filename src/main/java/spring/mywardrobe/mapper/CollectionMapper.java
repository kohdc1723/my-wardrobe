package spring.mywardrobe.mapper;

import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.dto.collection.CollectionResponse;

public class CollectionMapper {
    public static CollectionResponse mapToCollectionResponse(Collection collection) {
        return new CollectionResponse(
                collection.getId(),
                collection.getName(),
                collection.getUser().getId()
        );
    }
}
