package spring.mywardrobe.dto.collection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionCreateRequest {
    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "userId is required")
    private Long userId;
}
