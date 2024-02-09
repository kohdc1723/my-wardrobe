package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.collection.CollectionCreateRequest;
import spring.mywardrobe.dto.collection.CollectionResponse;
import spring.mywardrobe.mapper.CollectionMapper;
import spring.mywardrobe.repository.CollectionRepository;
import spring.mywardrobe.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    public CollectionResponse createCollection(CollectionCreateRequest collectionCreateRequest) {
        String name = collectionCreateRequest.getName();
        Long userId = collectionCreateRequest.getUserId();
        User user = userRepository.findOne(userId);

        Collection collection = new Collection(name, user);

        collectionRepository.save(collection);

        return CollectionMapper.mapToCollectionResponse(collection);
    }
}
