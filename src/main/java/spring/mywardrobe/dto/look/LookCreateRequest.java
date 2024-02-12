package spring.mywardrobe.dto.look;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LookCreateRequest {
    private String name;
    private Long userId;
    private List<Long> clothIds;
    private List<Long> keywordIds;
}
