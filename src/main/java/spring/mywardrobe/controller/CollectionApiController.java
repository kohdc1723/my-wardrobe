package spring.mywardrobe.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.dto.collection.CollectionCreateRequest;
import spring.mywardrobe.dto.collection.CollectionResponse;
import spring.mywardrobe.dto.collection.CollectionUpdateRequest;
import spring.mywardrobe.service.CollectionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/collections")
public class CollectionApiController {
    private final CollectionService collectionService;

    // POST - /api/collections
    @PostMapping
    public ResponseEntity<CollectionResponse> createCollection(
            @RequestBody @Valid CollectionCreateRequest collectionCreateRequest
    ) {
        CollectionResponse collectionResponse = collectionService.createCollection(collectionCreateRequest);

        return new ResponseEntity<>(collectionResponse, HttpStatus.CREATED);
    }

    // GET - /api/collections/{id}
    @GetMapping("{id}")
    public ResponseEntity<CollectionResponse> getCollectionById(
            @PathVariable("id") Long id
    ) {
        CollectionResponse collectionResponse = collectionService.getCollectionById(id);

        return new ResponseEntity<>(collectionResponse, HttpStatus.OK);
    }

    // PATCH - /api/collections/{id}
    @PatchMapping("{id}")
    public ResponseEntity<CollectionResponse> updateCollection(
            @PathVariable("id") Long id,
            @RequestBody @Valid CollectionUpdateRequest collectionUpdateRequest
    ) {
        CollectionResponse collectionResponse = collectionService.updateCollection(id, collectionUpdateRequest);

        return new ResponseEntity<>(collectionResponse, HttpStatus.OK);
    }

    // DELETE - /api/collections/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCollection(
            @PathVariable("id") Long id
    ) {
        collectionService.deleteCollection(id);

        String message = "Collection is successfully deleted";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
