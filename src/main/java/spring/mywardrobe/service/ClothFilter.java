package spring.mywardrobe.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.domain.Season;
import spring.mywardrobe.domain.User;

import java.util.List;

@Data
@AllArgsConstructor
public class ClothFilter {
    private Long userId;
    private Long collectionId;
    private String name;
    private String brand;
    private List<Season> seasons;
}
