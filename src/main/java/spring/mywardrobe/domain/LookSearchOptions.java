package spring.mywardrobe.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LookSearchOptions {
    private Long userId;
    private String name;
    private List<String> keywords;
}
