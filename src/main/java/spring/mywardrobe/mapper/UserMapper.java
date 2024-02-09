package spring.mywardrobe.mapper;

import spring.mywardrobe.domain.User;
import spring.mywardrobe.dto.user.UserCreateRequest;
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

    public static User mapToUser(UserCreateRequest userCreateRequest) {
        return new User(
                userCreateRequest.getEmail(),
                userCreateRequest.getPassword(),
                userCreateRequest.getFirstname(),
                userCreateRequest.getLastname()
        );
    }
}
