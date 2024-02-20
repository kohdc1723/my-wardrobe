package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.collection.CollectionCreateRequest;
import spring.mywardrobe.dto.collection.CollectionResponse;
import spring.mywardrobe.dto.collection.CollectionUpdateRequest;
import spring.mywardrobe.exception.RestApiException;
import spring.mywardrobe.exception.errorCode.CustomErrorCode;
import spring.mywardrobe.mapper.CollectionMapper;
import spring.mywardrobe.repository.CollectionRepository;
import spring.mywardrobe.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    public CollectionResponse createCollection(CollectionCreateRequest collectionCreateRequest) {
        String name = collectionCreateRequest.getName();
        Long userId = collectionCreateRequest.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.USER_NOT_FOUND));

        Collection collection = new Collection(name, user);

        collectionRepository.save(collection);

        return CollectionMapper.mapToCollectionResponse(collection);
    }

    public CollectionResponse getCollectionById(Long id) {
        Collection collection = collectionRepository.findById(id);

        return CollectionMapper.mapToCollectionResponse(collection);
    }

    public List<CollectionResponse> getCollectionByUser(Long userId) {
        List<Collection> collectionList = collectionRepository.findByUser(userId);

        return collectionList.stream()
                .map(CollectionMapper::mapToCollectionResponse)
                .toList();
    }

    public CollectionResponse updateCollection(Long id, CollectionUpdateRequest collectionUpdateRequest) {
        String name = collectionUpdateRequest.getName();

        Collection collection = collectionRepository.findById(id);
        collection.updateCollection(name);

        return CollectionMapper.mapToCollectionResponse(collection);
    }

    public void deleteCollection(Long id) {
        collectionRepository.delete(id);
    }
}
