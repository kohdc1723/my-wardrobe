package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Keyword;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.keyword.CreateKeywordRequest;
import spring.mywardrobe.dto.keyword.KeywordResponse;
import spring.mywardrobe.exception.RestApiException;
import spring.mywardrobe.exception.errorCode.CustomErrorCode;
import spring.mywardrobe.mapper.KeywordMapper;
import spring.mywardrobe.repository.KeywordRepository;
import spring.mywardrobe.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class KeywordService {
    private final KeywordRepository keywordRepository;
    private final UserRepository userRepository;

    public KeywordResponse createKeyword(CreateKeywordRequest createKeywordRequest) {
        Long userId = createKeywordRequest.getUserId();
        String name = createKeywordRequest.getName();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.USER_NOT_FOUND));
        Keyword keyword = new Keyword(name, user);

        keywordRepository.save(keyword);

        return KeywordMapper.mapToKeywordResponse(keyword);
    }

    @Transactional(readOnly = true)
    public KeywordResponse getKeywordById(Long id) {
        Keyword keyword = keywordRepository.findById(id);

        return KeywordMapper.mapToKeywordResponse(keyword);
    }

    @Transactional(readOnly = true)
    public List<KeywordResponse> getKeywordsByUser(Long userId) {
        List<Keyword> keywordList = keywordRepository.findByUser(userId);

        return keywordList.stream()
                .map(KeywordMapper::mapToKeywordResponse)
                .toList();
    }

    public KeywordResponse updateKeyword(Long id, String name) {
        Keyword keyword = keywordRepository.findById(id);

        keyword.updateKeyword(name);

        return KeywordMapper.mapToKeywordResponse(keyword);
    }

    public void deleteKeyword(Long id) {
        keywordRepository.delete(id);
    }
}
