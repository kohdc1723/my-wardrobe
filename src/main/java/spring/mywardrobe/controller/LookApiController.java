package spring.mywardrobe.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.dto.look.LookCreateRequest;
import spring.mywardrobe.dto.look.LookResponse;
import spring.mywardrobe.dto.look.LookUpdateRequest;
import spring.mywardrobe.service.LookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/looks")
public class LookApiController {
    private final LookService lookService;

    // POST - /api/looks
    @PostMapping
    public ResponseEntity<LookResponse> createLook(
            @RequestBody LookCreateRequest lookCreateRequest
    ) {
        LookResponse lookResponse = lookService.createLook(lookCreateRequest);

        return new ResponseEntity<>(lookResponse, HttpStatus.CREATED);
    }

    // GET - /api/looks/{id}
    @GetMapping("{id}")
    public ResponseEntity<LookResponse> getLookById(
            @PathVariable("id") Long id
    ) {
        LookResponse lookResponse = lookService.getLookById(id);

        return new ResponseEntity<>(lookResponse, HttpStatus.OK);
    }

    // GET - /api/looks?userId={userId}&keywordId={keywordId}
    @GetMapping
    public ResponseEntity<List<LookResponse>> getLooks(
            @Nonnull @RequestParam("userId") Long userId,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("keywordId") List<Long> keywordIds
    ) {
        List<LookResponse> lookResponseList = lookService.getLooks(userId, name, keywordIds);

        return new ResponseEntity<>(lookResponseList, HttpStatus.OK);
    }

    // PUT - /api/looks/{id}
    @PutMapping("{id}")
    public ResponseEntity<LookResponse> updateLook(
            @PathVariable("id") Long id,
            @RequestBody LookUpdateRequest lookUpdateRequest
    ) {
        LookResponse lookResponse = lookService.updateLook(id, lookUpdateRequest);

        return new ResponseEntity<>(lookResponse, HttpStatus.OK);
    }

    // DELETE - /api/looks/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLook(
            @PathVariable("id") Long id
    ) {
        lookService.deleteLook(id);

        String message = "Look is successfully deleted";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
