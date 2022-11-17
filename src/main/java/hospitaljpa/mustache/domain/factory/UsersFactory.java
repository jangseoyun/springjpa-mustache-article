package hospitaljpa.mustache.domain.factory;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserJoinResponse;
import hospitaljpa.mustache.domain.entity.Users;

public class UsersFactory {

    public static Users toUsers(UserJoinRequest userJoinRequest) {
        return new Users(
                  userJoinRequest.getUsername()
                , userJoinRequest.getPassword());
    }

    public static UserJoinResponse toUserJoinResponse(Users users) {
        return new UserJoinResponse(
                users.getUsername()
                ,setMessage(users.getId())
        );
    }

    private static String setMessage(Long id) {
        if (id == null) {
            return "이미 가입되어있는 아이디 입니다";
        }

        return "가입이 완료 되었습니다";
    }
}
