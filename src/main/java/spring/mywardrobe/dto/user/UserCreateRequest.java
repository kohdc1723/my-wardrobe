package spring.mywardrobe.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
