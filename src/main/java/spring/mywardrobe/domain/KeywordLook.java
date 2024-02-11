package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keyword_look")
public class KeywordLook {
    @Id
    @GeneratedValue
    @Column(name = "keyword_look_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "look_id")
    private Look look;

    public KeywordLook(Keyword keyword, Look look) {
        this.keyword = keyword;
        this.look = look;
    }
}
