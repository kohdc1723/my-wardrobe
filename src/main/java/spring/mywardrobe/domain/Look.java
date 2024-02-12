package spring.mywardrobe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "look", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClothLook> clothLooks = new ArrayList<>();

    @OneToMany(mappedBy = "look", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KeywordLook> keywordLooks = new ArrayList<>();

    public Look(String name, User user, List<Cloth> clothList, List<Keyword> keywordList) {
        this.name = name;
        this.user = user;
        setClothes(clothList);
        setKeywords(keywordList);
    }

    public void updateLook(String name, List<Cloth> clothList, List<Keyword> keywordList) {
        this.name = name;
        setClothes(clothList);
        setKeywords(keywordList);
    }

    private void setClothes(List<Cloth> clothList) {
        if (clothList != null && !clothList.isEmpty()) {
            this.clothLooks.clear();

            clothList.stream()
                    .map(cloth -> new ClothLook(cloth, this))
                    .forEach(this.clothLooks::add);
        }
    }

    private void setKeywords(List<Keyword> keywordList) {
        if (keywordList != null && !keywordList.isEmpty()) {
            this.keywordLooks.clear();

            keywordList.stream()
                    .map(keyword -> new KeywordLook(keyword, this))
                    .forEach(this.keywordLooks::add);
        }
    }
}
