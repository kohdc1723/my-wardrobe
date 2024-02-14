package spring.mywardrobe.dto.cloth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "brand is required")
    private String brand;

    private String imageUrl;

    private List<Season> seasons;

    private Long collectionId;

    @NotNull(message = "userId is required")
    private Long userId;
}
