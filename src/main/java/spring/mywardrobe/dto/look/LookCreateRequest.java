package spring.mywardrobe.dto.look;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LookCreateRequest {
    private String name;
    private Long userId;
    private List<Long> clothIds;
    private List<Long> keywordIds;
}
