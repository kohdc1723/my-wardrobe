package spring.mywardrobe.dto.cloth;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring.mywardrobe.domain.Season;
import spring.mywardrobe.dto.collection.CollectionResponse;

import java.util.List;

@Data
@AllArgsConstructor
public class ClothResponse {
    private Long id;
    private String name;
    private String brand;
    private String imageUrl;
    private List<Season> seasons;
    private CollectionResponse collection;
}
