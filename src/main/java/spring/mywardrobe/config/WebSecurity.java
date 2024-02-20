package spring.mywardrobe.config;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import spring.mywardrobe.domain.User;

import java.util.function.Supplier;

@Component
public class WebSecurity {
    public boolean isUserIdMatch(Supplier<Authentication> authentication, String id) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return false;
        }

        String currentId = ((User) authentication.get().getPrincipal()).getId().toString();

        return currentId.equals(id);
    }
}
