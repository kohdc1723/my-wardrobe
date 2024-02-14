package spring.mywardrobe.dto.look;

import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LookUpdateRequest {
    private String name;
    private List<Long> clothIds;
    private List<Long> keywordIds;
}
