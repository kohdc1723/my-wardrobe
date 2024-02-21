package spring.mywardrobe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import spring.mywardrobe.domain.Cloth;
import spring.mywardrobe.domain.User;
import spring.mywardrobe.exception.RestApiException;
import spring.mywardrobe.exception.errorCode.CustomErrorCode;
import spring.mywardrobe.repository.ClothRepository;

import java.util.Objects;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class WebSecurity {
    private ClothRepository clothRepository;

    public boolean isUserIdMatch(Supplier<Authentication> authentication, String id) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return false;
        }

        String currentId = ((User) authentication.get().getPrincipal()).getId().toString();

        return currentId.equals(id);
    }

    public boolean isClothOwner(Supplier<Authentication> authentication, String clothId) {
        Long id = Long.valueOf(clothId);
        Cloth cloth = clothRepository.findById(id)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND));

        Long clothOwner = cloth.getUser().getId();
        Long currentUserId = ((User) authentication.get().getPrincipal()).getId();

        return Objects.equals(clothOwner, currentUserId);
    }
}
