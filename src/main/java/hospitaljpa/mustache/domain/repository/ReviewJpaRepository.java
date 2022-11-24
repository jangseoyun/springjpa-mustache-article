package hospitaljpa.mustache.domain.repository;

import hospitaljpa.mustache.domain.entity.HospitalReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewJpaRepository extends JpaRepository<HospitalReview, Long> {
}
