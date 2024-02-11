package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.*;
import spring.mywardrobe.dto.look.LookCreateRequest;
import spring.mywardrobe.dto.look.LookResponse;
import spring.mywardrobe.mapper.LookMapper;
import spring.mywardrobe.repository.ClothRepository;
import spring.mywardrobe.repository.KeywordRepository;
import spring.mywardrobe.repository.LookRepository;
import spring.mywardrobe.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LookService {
    private final LookRepository lookRepository;
    private final UserRepository userRepository;
    private final ClothRepository clothRepository;
    private final KeywordRepository keywordRepository;

    public LookResponse createLook(LookCreateRequest lookCreateRequest) {
        Long userId = lookCreateRequest.getUserId();
        List<Long> clothIds = lookCreateRequest.getClothIds();
        List<Long> keywordIds = lookCreateRequest.getKeywordIds();

        User user = userRepository.findOne(userId);
        List<Cloth> clothList = clothIds.stream().map(clothRepository::findOne).toList();
        List<Keyword> keywordList = keywordIds.stream().map(keywordRepository::findById).toList();

        Look look = new Look(lookCreateRequest.getName(), user, clothList, keywordList);

        lookRepository.save(look);

        return LookMapper.mapToLookResponse(look);
    }

    public List<LookResponse> getAllLooks(Long userId, String name, List<String> keywords) {
        LookSearchOptions lookSearchOptions = new LookSearchOptions(userId, name, keywords);

        List<Look> lookList = lookRepository.findAll(lookSearchOptions);

        return lookList.stream()
                .map(LookMapper::mapToLookResponse)
                .toList();
    }
}
