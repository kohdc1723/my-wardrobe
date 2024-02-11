package spring.mywardrobe.controller;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.dto.look.LookCreateRequest;
import spring.mywardrobe.dto.look.LookResponse;
import spring.mywardrobe.service.LookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/looks")
public class LookApiController {
    private final LookService lookService;

    // POST - /api/looks
    @PostMapping
    public ResponseEntity<LookResponse> createLook(@RequestBody LookCreateRequest lookCreateRequest) {
        LookResponse lookResponse = lookService.createLook(lookCreateRequest);

        return new ResponseEntity<>(lookResponse, HttpStatus.CREATED);
    }

    // GET - /api/looks?userId={userId}&keyword={keyword}
    @GetMapping
    public ResponseEntity<List<LookResponse>> getAllLooks(
            @Nonnull @RequestParam("userId") Long userId,
            @Nullable @RequestParam("name") String name,
            @Nullable @RequestParam("keyword") List<String> keywords
    ) {
        List<LookResponse> lookResponseList = lookService.getAllLooks(userId, name, keywords);

        return new ResponseEntity<>(lookResponseList, HttpStatus.OK);
    }
}
