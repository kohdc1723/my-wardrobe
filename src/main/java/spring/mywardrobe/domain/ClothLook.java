package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cloth_look")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClothLook {
    @Id
    @GeneratedValue
    @Column(name = "cloth_look_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cloth_id")
    private Cloth cloth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "look_id")
    private Look look;

    public ClothLook(Cloth cloth, Look look) {
        this.cloth = cloth;
        this.look = look;
    }
}
