package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cloth")
public class Cloth {
    @Id
    @GeneratedValue
    @Column(name = "cloth_id")
    private Long id;

    @Column(name = "cloth_name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "seasons")
    @ElementCollection(targetClass = Season.class)
    @CollectionTable(name = "seasons", joinColumns = @JoinColumn(name = "cloth_id"))
    @Enumerated(EnumType.STRING)
    private List<Season> seasons = new ArrayList<>();

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    public Cloth(String name, String brand, String imageUrl, List<Season> seasons, User user, Collection collection) {
        this.name = name;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.seasons = seasons;
        this.user = user;
        this.collection = collection;
    }

    public void updateCloth(String name, String brand, String imageUrl, List<Season> seasons, Collection collection) {
        this.name = Objects.requireNonNullElse(name, this.name);
        this.brand = Objects.requireNonNullElse(brand, this.brand);
        this.imageUrl = Objects.requireNonNullElse(imageUrl, this.imageUrl);
        this.seasons = Objects.requireNonNullElse(seasons, this.seasons);
        this.collection = Objects.requireNonNullElse(collection, this.collection);
    }
}
