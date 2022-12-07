package hospitaljpa.mustache.domain.entity;

import hospitaljpa.mustache.domain.UserRole;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "t_users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email_address")
    private String emailAddress;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
