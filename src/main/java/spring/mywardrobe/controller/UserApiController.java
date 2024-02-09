package spring.mywardrobe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.mywardrobe.dto.user.UserCreateRequest;
import spring.mywardrobe.dto.user.UserResponse;
import spring.mywardrobe.dto.user.UserUpdateRequest;
import spring.mywardrobe.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserApiController {
    private final UserService userService;

    // POST - /api/users
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserResponse userResponse = userService.createUser(userCreateRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    // GET - /api/users/{id}
    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserResponse userResponse = userService.getUserById(id);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // PUT - /api/users/{id}
    @PutMapping("{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        UserResponse userResponse = userService.updateUser(id, userUpdateRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // DELETE - /api/users/{id}
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);

        String message = "Successfully deleted";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
