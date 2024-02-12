package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.*;
import spring.mywardrobe.dto.look.LookCreateRequest;
import spring.mywardrobe.dto.look.LookResponse;
import spring.mywardrobe.dto.look.LookUpdateRequest;
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

        User user = userRepository.findById(userId);
        List<Cloth> clothList = clothIds.stream().map(clothRepository::findById).toList();
        List<Keyword> keywordList = keywordIds.stream().map(keywordRepository::findById).toList();

        Look look = new Look(lookCreateRequest.getName(), user, clothList, keywordList);

        lookRepository.save(look);

        return LookMapper.mapToLookResponse(look);
    }

    public LookResponse getLookById(Long id) {
        Look look = lookRepository.findById(id);

        return LookMapper.mapToLookResponse(look);
    }

    public List<LookResponse> getLooks(Long userId, String name, List<Long> keywordIds) {
        LookSearchOptions lookSearchOptions = new LookSearchOptions(userId, name, keywordIds);

        List<Look> lookList = lookRepository.find(lookSearchOptions);

        return lookList.stream()
                .map(LookMapper::mapToLookResponse)
                .toList();
    }

    public LookResponse updateLook(Long id, LookUpdateRequest lookUpdateRequest) {
        String name = lookUpdateRequest.getName();

        List<Cloth> clothList = clothRepository.findAllByIds(lookUpdateRequest.getClothIds());
        List<Keyword> keywordList = keywordRepository.findAllByIds(lookUpdateRequest.getKeywordIds());

        Look look = lookRepository.findById(id);
        look.updateLook(name, clothList, keywordList);

        return LookMapper.mapToLookResponse(look);
    }

    public void deleteLook(Long id) {
        lookRepository.delete(id);
    }
}
