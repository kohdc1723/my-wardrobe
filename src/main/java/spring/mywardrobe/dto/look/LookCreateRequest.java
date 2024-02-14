package spring.mywardrobe.dto.look;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LookCreateRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "userId is required")
    private Long userId;

    private List<Long> clothIds;

    private List<Long> keywordIds;
}
