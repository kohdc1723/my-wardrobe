package spring.mywardrobe.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.domain.Season;
import spring.mywardrobe.dto.cloth.ClothCreateRequest;
import spring.mywardrobe.dto.cloth.ClothResponse;
import spring.mywardrobe.dto.cloth.ClothUpdateRequest;
import spring.mywardrobe.service.ClothService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clothes")
public class ClothApiController {
    private final ClothService clothService;

    // POST - /api/clothes
    @PostMapping
    public ResponseEntity<ClothResponse> createCloth(
            @RequestBody ClothCreateRequest clothCreateRequest
    ) {
        ClothResponse clothResponse = clothService.createCloth(clothCreateRequest);

        return new ResponseEntity<>(clothResponse, HttpStatus.CREATED);
    }

    // GET - /api/clothes/{id}
    @GetMapping("{id}")
    public ResponseEntity<ClothResponse> getClothById(
            @PathVariable("id") Long id
    ) {
        ClothResponse clothResponse = clothService.getClothById(id);

        return new ResponseEntity<>(clothResponse, HttpStatus.OK);
    }

    // GET - /api/clothes?userId={userId}&collectionId={collectionId}&name={name}&brand={brand}&season={season}
    @GetMapping
    public ResponseEntity<List<ClothResponse>> getClothes(
            @Nonnull @RequestParam("userId") Long userId,
            @Nullable @RequestParam("collectionId") Long collectionId,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("brand") String brand,
            @Nullable @RequestParam("season") List<Season> seasons
    ) {
        List<ClothResponse> clothResponseList = clothService.getAllClothes(userId, collectionId, name, brand, seasons);

        return new ResponseEntity<>(clothResponseList, HttpStatus.OK);
    }

    // PUT - /api/clothes/{id}
    @PutMapping("{id}")
    public ResponseEntity<ClothResponse> updateCloth(
            @PathVariable("id") Long id,
            @RequestBody ClothUpdateRequest clothUpdateRequest
    ) {
        ClothResponse clothResponse = clothService.updateCloth(id, clothUpdateRequest);

        return new ResponseEntity<>(clothResponse, HttpStatus.OK);
    }

    // DELETE - /api/clothes/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCloth(
            @PathVariable("id") Long id
    ) {
        clothService.deleteCloth(id);

        String message = "Cloth is successfully deleted";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
