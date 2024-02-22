package spring.mywardrobe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import spring.mywardrobe.domain.*;
import spring.mywardrobe.exception.RestApiException;
import spring.mywardrobe.exception.errorCode.CustomErrorCode;
import spring.mywardrobe.repository.ClothRepository;
import spring.mywardrobe.repository.CollectionRepository;
import spring.mywardrobe.repository.KeywordRepository;
import spring.mywardrobe.repository.LookRepository;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class WebSecurity {
    private final ClothRepository clothRepository;
    private final CollectionRepository collectionRepository;
    private final LookRepository lookRepository;
    private final KeywordRepository keywordRepository;

    public AuthorizationDecision authorizeUser(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext context
    ) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return new AuthorizationDecision(false);
        }

        String userId = context.getVariables().get("id");
        String currentId = ((User) authentication.get().getPrincipal()).getId().toString();

        return new AuthorizationDecision(userId.equals(currentId));
    }

    public AuthorizationDecision authorizeClothOwner(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext context
    ) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return new AuthorizationDecision(false);
        }

        Long currentUserId = ((User) authentication.get().getPrincipal()).getId();

        Long clothId = Long.valueOf(context.getVariables().get("id"));
        Cloth cloth = clothRepository.findById(clothId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND));
        Long clothUserId = cloth.getUser().getId();

        return new AuthorizationDecision(currentUserId.equals(clothUserId));
    }

    public AuthorizationDecision authorizeLookOwner(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext context
    ) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return new AuthorizationDecision(false);
        }

        Long currentUserId = ((User) authentication.get().getPrincipal()).getId();

        Long lookId = Long.valueOf(context.getVariables().get("id"));
        Look look = lookRepository.findById(lookId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND));
        Long lookUserId = look.getUser().getId();

        return new AuthorizationDecision(currentUserId.equals(lookUserId));
    }

    public AuthorizationDecision authorizeCollectionOwner(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext context
    ) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return new AuthorizationDecision(false);
        }

        Long currentUserId = ((User) authentication.get().getPrincipal()).getId();

        Long collectionId = Long.valueOf(context.getVariables().get("id"));
        Collection collection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND));
        Long collectionUserId = collection.getUser().getId();

        return new AuthorizationDecision(currentUserId.equals(collectionUserId));
    }

    public AuthorizationDecision authorizeKeywordOwner(
            Supplier<Authentication> authentication,
            RequestAuthorizationContext context
    ) {
        if (!(authentication.get().getPrincipal() instanceof User)) {
            return new AuthorizationDecision(false);
        }

        Long currentUserId = ((User) authentication.get().getPrincipal()).getId();

        Long keywordId = Long.valueOf(context.getVariables().get("id"));
        Keyword keyword = keywordRepository.findById(keywordId)
                .orElseThrow(() -> new RestApiException(CustomErrorCode.RESOURCE_NOT_FOUND));
        Long keywordUserId = keyword.getUser().getId();

        return new AuthorizationDecision(currentUserId.equals(keywordUserId));
    }
}
