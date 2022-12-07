package hospitaljpa.mustache.controller;

import hospitaljpa.mustache.domain.dto.*;
import hospitaljpa.mustache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService userService;

    /*--------- 회원가입 ----------*/
    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUsername(), userDto.getEmailAddress()));
    }

    /*--------- 로그인 ----------*/
    @PostMapping("/login")
    public Response<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        return Response.success(new UserLoginResponse(token));
    }


}
