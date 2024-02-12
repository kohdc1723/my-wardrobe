package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.user.UserCreateRequest;
import spring.mywardrobe.dto.user.UserResponse;
import spring.mywardrobe.dto.user.UserUpdateRequest;
import spring.mywardrobe.mapper.UserMapper;
import spring.mywardrobe.repository.CollectionRepository;
import spring.mywardrobe.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;

    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        User user = UserMapper.mapToUser(userCreateRequest);

        Long userId = userRepository.save(user);
        initDefaultCollections(userId);

        return UserMapper.mapToUserResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id);

        return UserMapper.mapToUserResponse(user);
    }

    public UserResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(id);

        String email = userUpdateRequest.getEmail();
        String password = userUpdateRequest.getPassword();
        String firstname = userUpdateRequest.getFirstname();
        String lastname = userUpdateRequest.getLastname();

        user.updateUser(email, password, firstname, lastname);

        return UserMapper.mapToUserResponse(user);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId);

        user.delete();
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
