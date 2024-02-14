package spring.mywardrobe.controller;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.dto.keyword.CreateKeywordRequest;
import spring.mywardrobe.dto.keyword.KeywordResponse;
import spring.mywardrobe.dto.keyword.UpdateKeywordRequest;
import spring.mywardrobe.service.KeywordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/keywords")
public class KeywordApiController {
    private final KeywordService keywordService;

    // POST - /api/keywords
    @PostMapping
    public ResponseEntity<KeywordResponse> createKeyword(
            @RequestBody @Valid CreateKeywordRequest createKeywordRequest
    ) {
        KeywordResponse keywordResponse = keywordService.createKeyword(createKeywordRequest);

        return new ResponseEntity<>(keywordResponse, HttpStatus.CREATED);
    }

    // GET - /api/keywords/{id}
    @GetMapping("{id}")
    public ResponseEntity<KeywordResponse> getKeywordById(
            @PathVariable("id") Long id
    ) {
        KeywordResponse keywordResponse = keywordService.getKeywordById(id);

        return new ResponseEntity<>(keywordResponse, HttpStatus.OK);
    }

    // GET - /api/keywords?userId={userId}
    @GetMapping
    public ResponseEntity<List<KeywordResponse>> getKeywordByUser(
            @Nonnull @RequestParam("userId") Long userId
    ) {
        List<KeywordResponse> keywordResponseList = keywordService.getKeywordsByUser(userId);

        return new ResponseEntity<>(keywordResponseList, HttpStatus.OK);
    }

    // PUT - /api/keywords/{id}
    @PutMapping("{id}")
    public ResponseEntity<KeywordResponse> updateKeyword(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateKeywordRequest updateKeywordRequest
    ) {
        String name = updateKeywordRequest.getName();

        KeywordResponse keywordResponse = keywordService.updateKeyword(id, name);

        return new ResponseEntity<>(keywordResponse, HttpStatus.OK);
    }

    // DELETE - /api/keywords/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKeyword(
            @PathVariable("id") Long id
    ) {
        keywordService.deleteKeyword(id);

        String message = "Keyword is successfully created";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
