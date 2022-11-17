package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserJoinResponse;
import hospitaljpa.mustache.domain.entity.Users;
import hospitaljpa.mustache.domain.factory.UsersFactory;
import hospitaljpa.mustache.domain.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        Users saveUser = userJpaRepository.save(UsersFactory.toUsers(userJoinRequest));
        return UsersFactory.toUserJoinResponse(saveUser);
    }

}
