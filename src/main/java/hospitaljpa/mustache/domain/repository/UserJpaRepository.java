package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.username =:username")
    Optional<Users> findByUsername(@Param("username") String username);
}
