package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
}
