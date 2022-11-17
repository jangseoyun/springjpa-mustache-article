package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserResponse;
import hospitaljpa.mustache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserResponse> join(UserJoinRequest userJoinRequest) {
        UserResponse joinResult = userService.join(userJoinRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(joinResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        UserResponse user = userService.findUser(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }


}
