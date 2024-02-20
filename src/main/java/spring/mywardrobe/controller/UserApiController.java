package spring.mywardrobe.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.domain.Season;
import spring.mywardrobe.dto.cloth.ClothResponse;
import spring.mywardrobe.dto.collection.CollectionResponse;
import spring.mywardrobe.dto.keyword.KeywordResponse;
import spring.mywardrobe.dto.look.LookResponse;
import spring.mywardrobe.dto.user.UserResponse;
import spring.mywardrobe.dto.user.UserUpdateRequest;
import spring.mywardrobe.service.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApiController {
    private final UserService userService;
    private final ClothService clothService;
    private final CollectionService collectionService;
    private final LookService lookService;
    private final KeywordService keywordService;

    // GET - /api/users/{id}
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable("id") Long id
    ) {
        UserResponse userResponse = userService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // GET - /api/users/{userId}/clothes?collectionId={collectionId}&name={name}&brand={brand}&season={season}
    @GetMapping("{id}/clothes")
    public ResponseEntity<List<ClothResponse>> getClothes(
            @PathVariable("id") Long id,
            @Nullable @RequestParam("collectionId") Long collectionId,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("brand") String brand,
            @Nullable @RequestParam("season") List<Season> seasons
    ) {
        List<ClothResponse> clothResponseList = clothService.getAllClothes(id, collectionId, name, brand, seasons);

        return new ResponseEntity<>(clothResponseList, HttpStatus.OK);
    }

    // GET - /api/users/{userId}/collections
    @GetMapping("{id}/collections")
    public ResponseEntity<List<CollectionResponse>> getCollectionsByUser(
            @PathVariable("id") Long id
    ) {
        List<CollectionResponse> collectionResponseList = collectionService.getCollectionByUser(id);

        return new ResponseEntity<>(collectionResponseList, HttpStatus.OK);
    }

    // GET - /api/users/{userId}/looks?keywordId={keywordId}
    @GetMapping("{id}/looks")
    public ResponseEntity<List<LookResponse>> getLooks(
            @PathVariable("id") Long id,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("keywordId") List<Long> keywordIds
    ) {
        List<LookResponse> lookResponseList = lookService.getLooks(id, name, keywordIds);

        return new ResponseEntity<>(lookResponseList, HttpStatus.OK);
    }

    // GET - /api/users/{userId}/keywords
    @GetMapping("{id}/keywords")
    public ResponseEntity<List<KeywordResponse>> getKeywordByUser(
            @PathVariable("id") Long id
    ) {
        List<KeywordResponse> keywordResponseList = keywordService.getKeywordsByUser(id);

        return new ResponseEntity<>(keywordResponseList, HttpStatus.OK);
    }

    // PATCH - /api/users/{id}
    @PatchMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        UserResponse userResponse = userService.updateUser(id, userUpdateRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // DELETE - /api/users/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") Long id
    ) {
        userService.deleteUser(id);

        String message = "User is successfully deleted";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
