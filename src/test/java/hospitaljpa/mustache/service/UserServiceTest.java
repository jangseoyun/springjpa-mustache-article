package hospitaljpa.mustache.service;

import hospitaljpa.mustache.domain.dto.UserJoinRequest;
import hospitaljpa.mustache.domain.dto.UserResponse;
import hospitaljpa.mustache.domain.entity.Users;
import hospitaljpa.mustache.domain.repository.UserJpaRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("UserServiceTest")
class UserServiceTest {

    private UserJpaRepository userJpaRepository = Mockito.mock(UserJpaRepository.class);
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userJpaRepository);
    }

    @Test
    @DisplayName("userJoin 성공")
    void userJoin() {
        Mockito.when(userJpaRepository.save(any()))
                .thenReturn(new Users(1L, "seoyun", "23432"));

        UserResponse join = userService.join(new UserJoinRequest("seoyun", "23432"));
        assertEquals("가입이 완료 되었습니다", join.getMessage());
    }
}