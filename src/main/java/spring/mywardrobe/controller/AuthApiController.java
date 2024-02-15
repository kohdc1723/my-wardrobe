package spring.mywardrobe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.mywardrobe.dto.user.UserCreateRequest;
import spring.mywardrobe.dto.user.UserResponse;
import spring.mywardrobe.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(
            @RequestBody @Valid UserCreateRequest userCreateRequest
    ) {
        UserResponse userResponse = authService.register(userCreateRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
