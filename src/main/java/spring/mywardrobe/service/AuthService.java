package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.user.UserCreateRequest;
import spring.mywardrobe.dto.user.UserResponse;
import spring.mywardrobe.exception.RestApiException;
import spring.mywardrobe.exception.errorCode.CustomErrorCode;
import spring.mywardrobe.mapper.UserMapper;
import spring.mywardrobe.repository.CollectionRepository;
import spring.mywardrobe.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse register(UserCreateRequest userCreateRequest) {
        String email = userCreateRequest.getEmail();
        String rawPassword = userCreateRequest.getPassword();

        validateEmailDuplicate(email);

        userCreateRequest.setPassword(bCryptPasswordEncoder.encode(rawPassword));

        User user = UserMapper.mapToUser(userCreateRequest);

        Long userId = userRepository.save(user);
        initDefaultCollections(userId);

        return UserMapper.mapToUserResponse(user);
    }

    private void validateEmailDuplicate(String email) throws RestApiException {
        boolean isDuplicate = userRepository.existsByEmail(email);

        if (isDuplicate) {
            throw new RestApiException(CustomErrorCode.EMAIL_DUPLICATE);
        }
    }

    private void initDefaultCollections(Long userId) {
        User user = userRepository.findById(userId);

        List<Collection> collectionList = Arrays.asList(
                new Collection("Tops", user),
                new Collection("Bottoms", user),
                new Collection("Shoes", user),
                new Collection("Outerwear", user),
                new Collection("Accessories", user)
        );

        collectionRepository.saveAll(collectionList);
    }
}
