package spring.mywardrobe.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
            @Valid @RequestBody ClothCreateRequest clothCreateRequest
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

    // PATCH - /api/clothes/{id}
    @PatchMapping("{id}")
    public ResponseEntity<ClothResponse> updateCloth(
            @PathVariable("id") Long id,
            @Valid @RequestBody ClothUpdateRequest clothUpdateRequest
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
