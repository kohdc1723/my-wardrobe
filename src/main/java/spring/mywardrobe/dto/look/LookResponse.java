package spring.mywardrobe.dto.look;

import lombok.AllArgsConstructor;
import lombok.Data;
import spring.mywardrobe.dto.cloth.ClothResponse;
import spring.mywardrobe.dto.keyword.KeywordResponse;

import java.util.List;

@Data
@AllArgsConstructor
public class LookResponse {
    private Long id;
    private String name;
    private List<ClothResponse> clothes;
    private List<KeywordResponse> keywords;
}
