package spring.mywardrobe.dto.keyword;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateKeywordRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "userId is required")
    private Long userId;
}
