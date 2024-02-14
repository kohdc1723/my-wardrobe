package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "keyword")
public class Keyword {
    @Id
    @GeneratedValue
    @Column(name = "keyword_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Keyword(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public void updateKeyword(String name) {
        this.name = Objects.requireNonNullElse(name, this.name);
    }
}
