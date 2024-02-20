package spring.mywardrobe.mapper;

import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.auth.RegisterRequest;
import spring.mywardrobe.dto.user.UserResponse;

public class UserMapper {
    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname()
        );
    }

    public static User mapToUser(RegisterRequest registerRequest) {
        return new User(
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstname(),
                registerRequest.getLastname()
        );
    }
}
