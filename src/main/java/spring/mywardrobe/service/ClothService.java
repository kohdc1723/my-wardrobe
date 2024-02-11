package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.*;
import spring.mywardrobe.dto.cloth.ClothCreateRequest;
import spring.mywardrobe.dto.cloth.ClothResponse;
import spring.mywardrobe.dto.cloth.ClothUpdateRequest;
import spring.mywardrobe.mapper.ClothMapper;
import spring.mywardrobe.repository.CollectionRepository;
import spring.mywardrobe.repository.ClothRepository;
import spring.mywardrobe.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClothService {
    private final ClothRepository clothRepository;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;

    public ClothResponse createCloth(ClothCreateRequest clothCreateRequest) {
        User user = userRepository.findOne(clothCreateRequest.getUserId());
        Collection collection = collectionRepository.findById(clothCreateRequest.getCollectionId());

        Cloth cloth = new Cloth(
                clothCreateRequest.getName(),
                clothCreateRequest.getBrand(),
                clothCreateRequest.getImageUrl(),
                clothCreateRequest.getSeasons(),
                user,
                collection
        );
        clothRepository.save(cloth);

        return ClothMapper.mapToClothResponse(cloth);
    }

    public ClothResponse getClothById(Long clothId) {
        Cloth cloth = clothRepository.findOne(clothId);

        return ClothMapper.mapToClothResponse(cloth);
    }

    public List<ClothResponse> getAllClothes(
            Long userId,
            Long collectionId,
            String name,
            String brand,
            List<Season> seasons
    ) {
        ClothSearchOptions clothSearchOptions = new ClothSearchOptions(userId, collectionId, name, brand, seasons);

        List<Cloth> clothList = clothRepository.findAll(clothSearchOptions);

        return clothList.stream()
                .map(ClothMapper::mapToClothResponse)
                .collect(Collectors.toList());
    }

    public ClothResponse updateCloth(Long id, ClothUpdateRequest clothUpdateRequest) {
        Cloth cloth = clothRepository.findOne(id);

        String name = clothUpdateRequest.getName();
        String brand = clothUpdateRequest.getBrand();
        String imageUrl = clothUpdateRequest.getImageUrl();
        List<Season> seasons = clothUpdateRequest.getSeasons();
        Long collectionId = clothUpdateRequest.getCollectionId();

        Collection collection = collectionRepository.findById(collectionId);

        cloth.updateCloth(name, brand, imageUrl, seasons, collection);

        return ClothMapper.mapToClothResponse(cloth);
    }

    public void deleteCloth(Long id) {
        clothRepository.deleteOne(id);
    }
}
