package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserResponse;
import hospitaljpa.mustache.domain.entity.Users;
import hospitaljpa.mustache.domain.factory.UsersFactory;
import hospitaljpa.mustache.domain.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserResponse join(UserJoinRequest userJoinRequest) {
        Optional<Users> findUser = userJpaRepository.findByUsername(userJoinRequest.getUsername());

        if (findUser.isEmpty()) {
            Users saveUser = userJpaRepository.save(UsersFactory.toUsers(userJoinRequest));
            return UsersFactory.toUserJoinResponse(saveUser);
        }

        return joinFalse(userJoinRequest.getUsername());
    }

    public UserResponse findUser(Long id) {
        Optional<Users> getUser = userJpaRepository.findById(id);
        return UsersFactory.toUserGetResponse(getUser);
    }

    private UserResponse joinFalse(String username) {
        return new UserResponse( username, "이미 존재하는 아이디 입니다.");
    }

}
