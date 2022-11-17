package hospitaljpa.mustache.domain.factory;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserResponse;
import hospitaljpa.mustache.domain.entity.Users;

import java.util.Optional;

public class UsersFactory {

    public static Users toUsers(UserJoinRequest userJoinRequest) {
        return new Users(
                  userJoinRequest.getUsername()
                , userJoinRequest.getPassword());
    }

    public static UserResponse toUserJoinResponse(Users users) {
        return new UserResponse(
                  users.getUsername()
                , setMessage(users.getId())
        );
    }

    private static String setMessage(Long id) {
        if (id == null) {
            return "이미 존재하는 아이디 입니다";
        }

        return "가입이 완료 되었습니다";
    }

    public static UserResponse toUserGetResponse(Optional<Users> user) {
        return new UserResponse(
                  user.get().getUsername()
                , setFindMessage(user)
        );
    }

    private static String setFindMessage(Optional<Users> user) {
        if (user.isEmpty()) {
            return "존재하지 않는 아이디 입니다";
        }

        return "";
    }

}
