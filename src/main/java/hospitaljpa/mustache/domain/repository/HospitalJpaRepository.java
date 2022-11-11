package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalJpaRepository extends JpaRepository<Hospital, Long> {
}
