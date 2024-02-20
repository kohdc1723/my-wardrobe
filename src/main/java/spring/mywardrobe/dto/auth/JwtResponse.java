package spring.mywardrobe.dto.auth;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class JwtResponse {
    private String token;
}
