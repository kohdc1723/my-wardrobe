package spring.mywardrobe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClothSearchOptions {
    private Long userId;
    private Long collectionId;
    private String name;
    private String brand;
    private List<Season> seasons;
}
