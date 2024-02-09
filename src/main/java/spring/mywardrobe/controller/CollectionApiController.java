package spring.mywardrobe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.mywardrobe.dto.collection.CollectionCreateRequest;
import spring.mywardrobe.dto.collection.CollectionResponse;
import spring.mywardrobe.service.CollectionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/collections")
public class CollectionApiController {
    private final CollectionService collectionService;

    // POST - /api/collections
    @PostMapping
    public ResponseEntity<CollectionResponse> createCollection(
            @RequestBody CollectionCreateRequest collectionCreateRequest
    ) {
        CollectionResponse collectionResponse = collectionService.createCollection(collectionCreateRequest);

        return new ResponseEntity<>(collectionResponse, HttpStatus.CREATED);
    }
}
