package spring.mywardrobe.dto.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionResponse {
    private Long id;
    private String name;
    private Long userId;
}
