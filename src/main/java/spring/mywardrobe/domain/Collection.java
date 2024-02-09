package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "collection")
public class Collection {
    @Id @GeneratedValue
    @Column(name = "collection_id")
    private Long id;

    @Column(name = "collection_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Collection(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
