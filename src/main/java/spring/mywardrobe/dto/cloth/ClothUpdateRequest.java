package spring.mywardrobe.dto.cloth;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import spring.mywardrobe.domain.Season;

import java.util.List;

@Data
@AllArgsConstructor
public class ClothUpdateRequest {
    private String name;

    private String brand;

    private String imageUrl;

    private List<Season> seasons;

    private Long collectionId;
}
