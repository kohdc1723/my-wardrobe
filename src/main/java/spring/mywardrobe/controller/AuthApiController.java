package spring.mywardrobe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.mywardrobe.dto.auth.JwtResponse;
import spring.mywardrobe.dto.auth.LoginRequest;
import spring.mywardrobe.dto.auth.RegisterRequest;
import spring.mywardrobe.dto.user.UserResponse;
import spring.mywardrobe.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthApiController {
    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(
            @RequestBody @Valid RegisterRequest registerRequest
    ) {
        UserResponse userResponse = authService.register(registerRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody @Valid LoginRequest loginRequest
    ) {
        JwtResponse jwtResponse = authService.login(loginRequest);

        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }


//    @PostMapping("refresh")
//    public ResponseEntity<JwtResponse> refresh(
//            @RequestBody @Valid
//    )
}
