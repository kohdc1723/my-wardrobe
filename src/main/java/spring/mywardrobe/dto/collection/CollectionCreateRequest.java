package spring.mywardrobe.dto.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionCreateRequest {
    private String name;
    private Long userId;
}
