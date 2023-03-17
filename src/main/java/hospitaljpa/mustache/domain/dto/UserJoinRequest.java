package hospitaljpa.mustache.domain.dto;

import hospitaljpa.mustache.domain.UserRole;
import hospitaljpa.mustache.domain.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class UserJoinRequest {
    private String username;
    private String password;
    private String emailAddress;

    public Users toEntity(String password) {
        return Users.builder()
                .username(this.username)
                .password(password)
                .emailAddress(this.emailAddress)
                .role(UserRole.USER)
                .build();
    }
}
