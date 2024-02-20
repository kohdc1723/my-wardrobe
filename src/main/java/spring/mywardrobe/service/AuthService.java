package spring.mywardrobe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.mywardrobe.domain.Collection;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.auth.JwtResponse;
import spring.mywardrobe.dto.auth.LoginRequest;
import spring.mywardrobe.dto.auth.RegisterRequest;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        validateEmailDuplicate(email);

        String rawPassword = registerRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        registerRequest.setPassword(encodedPassword);

        User user = UserMapper.mapToUser(registerRequest);
        Long userId = userRepository.save(user);

        initDefaultCollections(userId);

        return UserMapper.mapToUserResponse(user);
    }

    public JwtResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.EMAIL_DOES_NOT_EXIST));

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password);

        try {
            authenticationManager.authenticate(authToken);
        } catch (AuthenticationException e) {
            throw new RestApiException(CustomErrorCode.WRONG_PASSWORD);
        }

        String token = jwtService.generateToken(user);

        return new JwtResponse(token);
    }

    private void validateEmailDuplicate(String email) throws RestApiException {
        boolean isDuplicate = userRepository.existsByEmail(email);

        if (isDuplicate) {
            throw new RestApiException(CustomErrorCode.EMAIL_DUPLICATE);
        }
    }

    private void initDefaultCollections(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.USER_NOT_FOUND));

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
