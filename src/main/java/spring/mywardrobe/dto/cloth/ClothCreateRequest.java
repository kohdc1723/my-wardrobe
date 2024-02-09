package spring.mywardrobe.dto.cloth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.mywardrobe.domain.Season;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClothCreateRequest {
    private String name;
    private String brand;
    private String imageUrl;
    private List<Season> seasons;
    private Long collectionId;
    private Long userId;
}
