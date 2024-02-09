package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "look")
public class Look {
    @Id
    @GeneratedValue
    @Column(name = "look_id")
    private Long id;

    @Column(name = "look_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Look(String name, User user) {
        this.name = name;
        this.user = user;
    }
}
