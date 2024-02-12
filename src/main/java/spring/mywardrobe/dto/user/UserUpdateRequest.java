package spring.mywardrobe.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
}
