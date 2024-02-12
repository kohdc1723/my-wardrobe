package spring.mywardrobe.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class LookSearchOptions {
    private Long userId;
    private String name;
    private List<Long> keywordIds;
}
