package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserJoinResponse;
import hospitaljpa.mustache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<UserJoinResponse> join(UserJoinRequest userJoinRequest) {
        UserJoinResponse joinResult = userService.join(userJoinRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(joinResult);
    }


}
