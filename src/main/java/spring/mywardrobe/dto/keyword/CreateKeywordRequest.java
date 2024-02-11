package spring.mywardrobe.dto.keyword;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateKeywordRequest {
    private String name;
    private Long userId;
}
